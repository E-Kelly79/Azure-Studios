package model;

import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

public class Movies {
	
	
	static Long counter = 0l;

	public Long id = (long) 0;
	public String title;
	public String year;
	public String url;
	
	public List<Ratings> theMovies = new ArrayList<>();

	public Movies() {
	}

	public Movies (String title, String year, String url)
	  {
	    this.id        = counter++;
	    this.title  = title;
	    this.year = year;
	    this.url = url;
	  }

	@Override
	public String toString() {
		return toStringHelper(this)
				.addValue(id)
				.addValue(title)
				.addValue(year)
				.addValue(url)
				.addValue(theMovies)
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.title, this.year, this.url);
	}
}
