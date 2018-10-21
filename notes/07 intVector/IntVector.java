package intVector;

public class IntVector {
	/**
	* Dimension (length) of this vector.
	*/
	private int dim;
	
	/**
	* The coordinates of this vector.
	*/
	private int[] coords;
	
	/**
	* Constructs a zero vector of the given dimension.
	* @param dimension
	* @throws IllegalArgumentException if the dimension is less than
	* or equal to zero
	*/
	public IntVector(int dimension)
	{
		if (dimension <= 0) 
			throw new IllegalArgumentException();
		dim = dimension;
		coords = new int[dim]; // default to all zeros
	}
	
	/**
	* Constructs a deep copy of the given vector.
	* @param existing an existing IntVector to be copied
	*/
	public IntVector(IntVector existing)
	{
		dim = existing.dim;
		coords = new int[dim];
	
		// Alternative: use System.arraycopy(...)
		for (int i = 0; i < dim; ++i)
		{
			coords[i] = existing.coords[i];
		}
	}
	
	/**
	* Returns the coordinate at the given index.
	* @param index position of the coordinate to return
	* @return the coordinate at the given index
	* @throws IndexOutOfBoundsException if the given index
	* is less than zero or is greater than or equal to
	* the dimension of the vector
	*/
	public int get(int index)
	{
		return coords[index];
	}
	
	/**
	* Sets the coordinate at the given index to the given value.
	* @param index position of the coordinate to set
	* @param value the coordinate's new value
	* @throws IndexOutOfBoundsException if the given index
	* is less than zero or is greater than or equal to
	* the dimension of the vector
	*/
	public void set(int index, int value)
	{
		coords[index] = value;
	}
	
	/**
	* Returns the dimension of this vector.
	* @return the dimension of this vector.
	*/
	public int dimension()
	{
		return dim;
	}
	
	/**
	* Returns the dot product of this vector and the given vector.
	* @param v the given vector
	* @throws IllegalArgumentException if the given vector does
	* not have the same dimension as this vector
	*/
	public int dotProduct(IntVector v)
	{
		int result = 0;
		for (int i = 0; i < v.dim; ++i)
		{
			result += coords[i] * v.coords[i];
		}
		return result;
	}
	
	/**
	* Determines whether this vector is equal to the given Object.
	* @return true if the given Object is an IntVector with the
	* same dimension and the same coordinates as this one.
	*/
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		IntVector other = (IntVector) obj;
		
		if (dim == other.dim)
		{
			// Check whether all coordinates are the same
			//
			// Alternatively: use Arrays.equals(coords, other.coords)
			// Note that coords.equals(other.coords) will NOT work
			for (int i = 0; i < dim; ++i)
			{
				if (coords[i] != other.coords[i])
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	* Creates a deep copy of this vector.
	* @return a deep copy of this object.
	*/
	@Override
	public IntVector clone()
	{
		try
		{
			IntVector copy = (IntVector) super.clone();
			
			// Object.clone() copies fields, now make it into deep copy
			copy.coords = new int[dim];
			for (int i = 0; i < dim; ++i)
			{
				copy.coords[i] = coords[i];
			}
			return copy;
		}
		catch (CloneNotSupportedException e)
		{
			// should never happen...
			return null;
		}
	}
}
