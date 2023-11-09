package ch.rasc.openai4j.common;
public enum SortOrder {
	ASC("asc"), DESC("desc");

	private final String value;

	SortOrder(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}