package nthu.cs.excelsior.service.objectify;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nthu.cs.excelsior.object.Note;
import nthu.cs.excelsior.object.Profile;
import nthu.cs.excelsior.object.Video;

import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify needs every persistable object to be registered. This class registers all domain 
 * objects at the time when the {@link ServletContext} is initialized. 
 */
public final class ObjectifyInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		ObjectifyService.register(Video.class);
		ObjectifyService.register(Profile.class);
		ObjectifyService.register(Note.class);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// do nothing
	}
}
