package person.mikepatterson.sampleapp.presenter;

import person.mikepatterson.sampleapp.viewmodel.BaseViewModel;

public abstract class BasePresenter {

    public interface PresenterListener<T extends BaseViewModel> {
        void onUpdateUi(T viewModel);
    }

    public abstract void register(PresenterListener listener);

    public abstract void unregister();
}
