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
package examples.basicRouter.redux.reducer

import scala.scalajs.js
import scala.scalajs.js.Dynamic._
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.ScalaJSDefined

import examples.basicRouter.redux.newActions._
import examples.basicRouter.redux.constants.ActionsTypes._
import examples.basicRouter.store._
import js.JSConverters._
import examples.basicRouter.redux.newActions.GenericAction

@ScalaJSDefined
object ReducerAccount extends js.Object {

  val accounts: js.Function2[UndefOr[AccountData], GenericAction, js.Any] = (stateBefore: UndefOr[AccountData], act: GenericAction) => {
    // val friends: js.Function  = (stateBefore: UndefOr[StateFriend], act: GenericAction) => {

    println("appel reducer Accounts")

    if (stateBefore.toOption == None) {
      new AccountData("georges", "lipka", 58, "", false, "", "comment1")
    } else {

      val state = stateBefore.toOption.get

      println("on est dans le reducer accounts action.type=" + act.`type`)

      act.`type`.toString match {
        case `ACCOUNT_LOAD` =>
          val action2 = act.asInstanceOf[ActionAccountLoad]
          action2.accountData

        case _ => {

          println("ReducerAccounnt case _ :" + act.`type`.toString)

          state
        }
      }
    }
  }

}