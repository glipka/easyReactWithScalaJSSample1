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