package model;
/*
 * Author: Eoin Kelly 
 * Student No: 20074820
 * Date: 03/11/17
 */
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Movies {
	
	
	static Long counter = 0l;

	public Long id;
	public String title;
	public String year;
	public String url;
	
	public List<Ratings> theMovies = new ArrayList<>();

	public Movies() {
	}

	public Movies (String title, String year, String url)
	  {
	    this.id     = counter++;
	    this.title  = title;
	    this.year 	= year;
	    this.url 	= url;
	  }

	@Override
	public String toString() {
		return toStringHelper(this)
				.addValue(id)
				.addValue(title)
				.addValue(year)
				.addValue(url)
				.addValue(theMovies + "\n")
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.title, this.year, this.url);
	}
	
	@Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Movies)
	    {
	      final Movies other = (Movies) obj;
	      return Objects.equal(title, other.title) 
	          && Objects.equal(year,  other.year)
	          && Objects.equal(url,  other.url)
	          && Objects.equal(theMovies,     other.theMovies);    
	    }
	    else
	    {
	      return false;
	    }
	  }
}
