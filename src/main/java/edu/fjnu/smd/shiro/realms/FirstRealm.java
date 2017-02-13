package edu.fjnu.smd.shiro.realms;

import edu.fjnu.smd.domain.User;
import edu.fjnu.smd.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstRealm extends AuthenticatingRealm {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		//2. 从 UsernamePasswordToken 中来获取 username
		String userNo = upToken.getUsername();

		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		User user=new User();
		user=userService.get(userNo);

		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if(user==null){
			throw new UnknownAccountException("用户不存在!");
		}

		//5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = user.getUserNo();
		//2). credentials: 密码.
		Object credentials = getPwd(user.getUserNo(),1024);

		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值.
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserNo());

		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;

	}

	private Object getPwd(String str , int hashIterations) {
		String hashAlgorithmName = "SHA1";
		Object credentials = userService.get(str).getUserPwd();
		Object salt = ByteSource.Util.bytes(str);

		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		return result;
	}
}
