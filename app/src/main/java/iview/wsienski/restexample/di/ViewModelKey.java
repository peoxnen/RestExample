package iview.wsienski.restexample.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.*;

/**
 * Created by Witold Sienski on 20.11.2018.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {
    Class<? extends ViewModel> value();
}