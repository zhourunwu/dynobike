package com.jeeplus.modules.sports.common.shiro;

import com.jeeplus.modules.sports.common.utils.StringUtil;
import com.jeeplus.modules.sports.entity.Users;
import com.jeeplus.modules.sports.service.UsersService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;


public class UserRealm extends AuthorizingRealm {
	private static Logger logger = Logger.getLogger(UserRealm.class);

	@Resource
	private UsersService userService;

	 public UserRealm() {
//	        super.setName(Constants.THE_REALM_NAME);
	        super.setCredentialsMatcher(new Sha256CredentialsMatcher());
	 }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	   System.out.println(" 由于加入了缓存, 此处只会load一次：doGetAuthorizationInfo.................");
	   String username = (String)principals.getPrimaryPrincipal();

		Users user = userService.getUserByAccount(username);
		if(null != user){
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

			return authorizationInfo;
		}
		return  null;
	}


	/**
	 * AuthenticationInfo represents a Subject's (aka user's) stored account information
	 * relevant to the authentication/log-in process only.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
			SimpleAuthenticationInfo authenticationInfo = null;
			ShiroUsernamePasswordToken usernamePasswordToke =(ShiroUsernamePasswordToken)token;
			String username = usernamePasswordToke.getUsername();
			  if( username != null && !"".equals(username) ){
					  authenticationInfo = new SimpleAuthenticationInfo(username, usernamePasswordToke.getBase64Password(), getName());
		              authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username+usernamePasswordToke.getSalt()));
			  }
	        return authenticationInfo;
	}
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		String userAccount= (String)principals.getPrimaryPrincipal();
		Users u = userService.getUserByAccount(userAccount);
		if(!StringUtil.isEmpty(userAccount)){
			return true;
		}
		Permission p = this.getPermissionResolver().resolvePermission(permission);
		return this.isPermitted(principals, p);
	}

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
