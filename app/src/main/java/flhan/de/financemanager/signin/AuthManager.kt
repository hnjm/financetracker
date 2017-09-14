package flhan.de.financemanager.signin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import flhan.de.financemanager.common.FirebaseClient
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Created by Florian on 09.09.2017.
 */
interface AuthManager {
    fun auth(token: String): Observable<AuthResult>
}

class AuthManagerImpl(
        private val firebaseClient: FirebaseClient
) : AuthManager {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun auth(token: String): Observable<AuthResult> {
        return Observable.create { e: ObservableEmitter<AuthResult> ->
            val credential = GoogleAuthProvider.getCredential(token, null)
            mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) firebaseClient.init()

                e.onNext(AuthResult(task.exception.toString(), task.isSuccessful))
                e.onComplete()
            }
        }
    }
}