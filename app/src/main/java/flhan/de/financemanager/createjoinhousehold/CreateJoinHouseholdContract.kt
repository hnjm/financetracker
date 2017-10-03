package flhan.de.financemanager.createjoinhousehold

import io.reactivex.Observable
import io.reactivex.subjects.Subject

/**
 * Created by Florian on 29.09.2017.
 */
interface CreateJoinHouseholdContract {
    interface View {
        var nameObservable: Observable<CharSequence>
        var emailObservable: Observable<CharSequence>
        var stateObservable: Observable<ViewState>
        var loadingSubject: Subject<Boolean>
        fun finish()
    }

    interface Presenter {
        var canSubmitObservable: Observable<Boolean>

        fun attach()
        fun detach()
        fun onDoneClick()
    }
}