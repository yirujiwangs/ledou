package utils.serialnum;

import java.util.UUID;

public class UuidSerialNumCreater extends BaseSerialNumCreater{
	
	static class instance{
		static UuidSerialNumCreater uuidSerialNumCreater = new UuidSerialNumCreater();
	}
	
	public static UuidSerialNumCreater getInstance(){
		return instance.uuidSerialNumCreater;
	}

	@Override
	public String create() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
