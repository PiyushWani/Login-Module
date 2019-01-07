package utilities;

import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import pojo.UserInfoPojo;

public class Hash
{
	SecureRandom random=null;
	PKCS5S2ParametersGenerator kdf=null;
	private int iterations = 200;
	private int keySize = 128;
	public void saltAndHash(UserInfoPojo tray)
	{
		random = new SecureRandom();
		byte []salt = random.generateSeed(20);
		tray.setSalt(salt);
		
		kdf = new PKCS5S2ParametersGenerator();
		kdf.init(tray.getPassword().getBytes(), tray.getSalt(), iterations);
		tray.setHash(((KeyParameter)kdf.generateDerivedMacParameters(keySize)).getKey());				
	}
	public void setHash(UserInfoPojo tray)
	{
		kdf = new PKCS5S2ParametersGenerator();
		kdf.init(tray.getPassword().getBytes(), tray.getSalt(), iterations);		
		tray.setHash(((KeyParameter)kdf.generateDerivedMacParameters(keySize)).getKey());
		System.out.println("New computed Hash for "+tray.getPassword()+": "+Arrays.toString(tray.getHash()));
	}
}
