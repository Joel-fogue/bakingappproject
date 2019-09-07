package com.udacity.projects.bakingapp.di;

import com.udacity.projects.bakingapp.data.network.RecipeIntentService;
import com.udacity.projects.bakingapp.di.modules.ContextModule;
import com.udacity.projects.bakingapp.di.modules.ExecutorsModule;
import com.udacity.projects.bakingapp.di.modules.NetworkModule;
import com.udacity.projects.bakingapp.di.modules.RoomModule;
import com.udacity.projects.bakingapp.ui.recipes.RecipeFragment;
import com.udacity.projects.bakingapp.widget.ListWidgetService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, NetworkModule.class, ExecutorsModule.class, ContextModule.class})
public interface BakingComponent {

    void inject(RecipeFragment fragment);

    void inject(RecipeIntentService service);

    void inject(ListWidgetService.ListRemoteViewsFactory service);

}
