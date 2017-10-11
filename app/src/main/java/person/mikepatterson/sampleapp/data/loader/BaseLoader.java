package person.mikepatterson.sampleapp.data.loader;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class BaseLoader<T> {

    protected LoaderListener<T> listener;

    public interface LoaderListener<T> {
        void onChange(T data);
    }

    public void register(@NonNull LoaderListener<T> listener) {
        this.listener = listener;
        addRealmListener();
    }

    public abstract void addRealmListener();

    @CallSuper
    public void unregister() {
        listener = null;
    }

    protected void notifyChange(T data) {
        if (listener == null) {
            return;
        }
        listener.onChange(data);
    }
}
