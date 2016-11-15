package examples.basicRouter.store
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import redux.Redux._
import redux._
import reduxForm._
import reduxForm.ReduxForm
import examples.basicRouter.redux.reducer.ReducerFriends
import examples.basicRouter.redux.reducer.ReducerAccount
import js.Dynamic.literal
import scala.collection.mutable.Map
import org.scalajs.dom
import examples.basicRouter.redux.newActions.GenericAction
import examples.basicRouter.redux.newActions.AccountData

@ScalaJSDefined
class StateFriend(val stateFriend: State1) extends js.Object

case class Friend(val id: Int, val name: String, val starred: Boolean)

@ScalaJSDefined
object Reducs extends js.Object {
  val form: js.Function = (stateBefore: js.Any, act: js.Any) => ReduxForm.reducer(stateBefore, act)
   val reduc2: js.Function = (stateBefore: js.UndefOr[StateFriend], act: GenericAction) => ReducerFriends.friends(stateBefore, act)
   val accountData: js.Function = (stateBefore: js.UndefOr[AccountData], act: GenericAction) => ReducerAccount.accounts(stateBefore, act)
}

@ScalaJSDefined
class State1(
  val friends: js.Array[Int],
  val friendsById: js.Array[Friend] // (id,(stared,literal())
  ) extends js.Object

// ----------------------------------------------------
// configuration du store qui va maintenir l'état 
// -------------------------------------------------------
object ConfigureStore {
  var store: Store[Nothing] = _
  val extension1 = js.Dynamic.global.window.__REDUX_DEVTOOLS_EXTENSION__
  val extension2 = js.Dynamic.global.window.__REDUX_DEVTOOLS_EXTENSION__()

  def getInstanceStore(): Store[_] = {
    val reducers = combineReducers(Reducs)
    store = createStore(reducers, extension2)
    return store
  }

}