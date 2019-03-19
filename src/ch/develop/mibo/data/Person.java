package ch.develop.mibo.data;

public class Person {
	
	protected String id;
	protected String lastName;
	protected String firstName;
	protected int password;

	public Person(String lastName, String firstName, String id) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
	}
	
	public Person(String lastName, String firstName, String id, int password) {
		this(lastName, firstName, id);
		this.password = password;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getPassword() {
		return password;
	}


	public String getId() {
		return id;
	}

	public String toString() {
		return lastName + " " + firstName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
