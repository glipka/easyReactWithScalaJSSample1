/*
 # Copyright 2016 Georges Lipka
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
*/
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