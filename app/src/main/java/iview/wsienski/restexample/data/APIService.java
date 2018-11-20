package iview.wsienski.restexample.data;

import io.reactivex.Observable;
import iview.wsienski.restexample.data.model.User;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by Witold Sienski on 20.11.2018.
 */
public interface APIService {

    @GET("users")
    Observable<List<User>> listUsers(@Query("since") int since);

}
