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
object ReducerFriends extends js.Object {

  val friends: js.Function2[UndefOr[StateFriend], GenericAction, js.Any] = (stateBefore: UndefOr[StateFriend], act: GenericAction) => {
    // val friends: js.Function  = (stateBefore: UndefOr[StateFriend], act: GenericAction) => {

    println("appel reducer friends")

    if (stateBefore.toOption == None) {
      new StateFriend(new State1(js.Array(1, 2, 3), js.Array(Friend(1, "theodore Roosevelt", false), Friend(2, "Abraham Lincoln", false), Friend(3, "George Washington", false))))

    } else {

      val state = stateBefore.toOption.get

      println("on est dans le reducer action.type=" + act.`type`)

      act.`type`.toString match {
        case `ADD_FRIEND` =>
          val action2 = act.asInstanceOf[ActionAddFriend]
          val newId = state.stateFriend.friends.length + 1
          new StateFriend(new State1(state.stateFriend.friends ++ Array(newId), state.stateFriend.friendsById ++ Array(Friend(newId, action2.name, false))))

        case `STAR_FRIEND` =>
          //        // pb sur le case avec l'objet dynamique
          val action2 = act.asInstanceOf[ActionStarFriend]
          val friendsById = state.stateFriend.friendsById.map {
            case (Friend(id, name, starred)) if id == action2.id => Friend(id, name, !starred)
            case (Friend(id, name, starred)) => Friend(id, name, starred)
          }
          new StateFriend(new State1(state.stateFriend.friends, friendsById))

        //    
        //        new State1(state.friends, friendsById)
        //
        case `DELETE_FRIEND` =>
          val action2 = act.asInstanceOf[ActionDeleteFriend]
           new StateFriend(new State1(state.stateFriend.friends.filter(id => id != action2.id), state.stateFriend.friendsById.filter(friend => friend.id != action2.id)))
        //       
        case `INIT_FRIEND` =>
            new StateFriend(new State1(js.Array(1, 2, 3), js.Array(Friend(1, "theodore Roosevelt", false), Friend(2, "Abraham Lincoln", false), Friend(3, "George Washington", false))))

        case _ => {

          println("ReducerFriends case _ :" + act.`type`.toString)

          state
        }
      }
    }
  }

}