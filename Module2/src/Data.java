public class Data{
	public String value;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Data) {
			Data other = (Data) obj;
			return this.value.equals(other.value);
		}
		return false;
	}
}