package com.kevin.base.reflect;

/**
 * @author kevin
 */
public class ReflectPoint {
	private int x;
	public int y;
	public String str1 = "ball";
	public String str2 = "basketball";
	public String str3 = "itcast";

	public ReflectPoint(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	/**
	* (not Javadoc)
	* Title: toString
	* Description:TODO【Please describe the overriding method in a word】
	* @return
	* @see Object#toString()
	*/
	@Override
	public String toString()
	{
		return "ReflectPoint [x=" + x + ", y=" + y + ", str1=" + str1 + ", str2=" + str2 + ", str3=" + str3 + "]";
	}

}
