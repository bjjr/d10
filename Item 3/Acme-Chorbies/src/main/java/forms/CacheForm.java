
package forms;

public class CacheForm {

	private long	timeout;


	public CacheForm() {
		super();
	}

	public CacheForm(final long timeout) {
		super();
		this.timeout = timeout;
	}

	public long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(final long timeout) {
		this.timeout = timeout;
	}

}
