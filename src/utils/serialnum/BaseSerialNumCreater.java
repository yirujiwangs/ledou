package utils.serialnum;

public abstract class BaseSerialNumCreater {
	private String msg;

	public BaseSerialNumCreater() {
		super();
	}


	public BaseSerialNumCreater(String msg) {
		super();
		this.msg = msg;
	}

	public abstract String create();

}
