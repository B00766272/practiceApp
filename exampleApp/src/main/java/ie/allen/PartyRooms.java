package ie.allen;

class PartyRooms
{
    private String room;
    private double capacity;
	private String feature;
	private String alcohol_allowed;

    PartyRooms(String room,double capacity,String feature, String alcohol_allowed)
    {
        this.room = room;
        this.capacity = capacity;
		this.feature = feature;
        this.alcohol_allowed = alcohol_allowed;
     
    }
	
	public String getRooms() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	public String getAlcoholAllowed() {
		return alcohol_allowed;
	}
	
	public void setAlcoholAllowed(String alcohol_allowed) {
		this.alcohol_allowed = alcohol_allowed;
	}
	
	public String getFeature() {
		return feature;
	}
	
	public void setFeature(String feature) {
		this.feature = feature;
	}
}