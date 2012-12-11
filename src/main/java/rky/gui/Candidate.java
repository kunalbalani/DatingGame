package rky.gui;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

	List<Attribute> attributes = new ArrayList<Attribute>();

	public List<Attribute> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) 
	{
		this.attributes = attributes;
	}
}
