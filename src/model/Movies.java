package model;
/*
 * Author: Eoin Kelly 
 * Student No: 20074820
 * Date: 03/11/17
 */

import java.util.HashMap;
import java.util.Map;
import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Movies implements Comparable<Movies>{
	
	
	static Long counter = 0l;

	public Long id;
	public String title;
	public String year;
	public String url;
	
	public Map<Long, Ratings> theMoviesRatings = new HashMap<>();

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
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.title, this.year, this.url);
	}
	
	@Override
	  public boolean equals(final Object obj){
	    if (obj instanceof Movies)
	    {
	      final Movies other = (Movies) obj;
	      return Objects.equal(title, other.title) 
	          && Objects.equal(year,  other.year)
	          && Objects.equal(url,  other.url);    
	    }
	    else
	    {
	      return false;
	    }
	  }
	
	public int compareTo(Movies movie) {
		return this.title.compareTo(movie.title);
	}
	
	
}
