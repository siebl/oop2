package ch.develop.milord;

public class BaseEntity {
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * Overloaded Method to allow equals based on the ID 
	 * @param obj The ID of the Object
	 * @return true if the ID matches the description
	 */
	public boolean equals(int obj) {
		return this.id == obj;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
