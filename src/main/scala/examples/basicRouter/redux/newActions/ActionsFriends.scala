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
package examples.basicRouter.redux.newActions
import examples.basicRouter.redux.constants.ActionsTypes._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction0
import scala.scalajs.js.Any.fromFunction1

@ScalaJSDefined
trait GenericAction extends js.Object {
  val `type`: String
}

@ScalaJSDefined
trait ActionAddFriend extends js.Object with GenericAction {
  val name: String
}

@ScalaJSDefined
trait ActionDeleteFriend extends js.Object with GenericAction {
  val id: Int
}

@ScalaJSDefined
trait ActionStarFriend extends js.Object with GenericAction {
  val id: Int
}
@ScalaJSDefined
object Actions extends js.Object  {

  val addFriend: js.Function1[String, ActionAddFriend] = (name1: String) => {
    new ActionAddFriend {
      val `type` = ADD_FRIEND
      val name = name1
    }
  }
  val deleteFriend: js.Function1[Int, ActionDeleteFriend] = (id1: Int) => {
    new ActionDeleteFriend {
      val `type` = DELETE_FRIEND
      val id = id1
    }
  }

  val starFriend: js.Function1[Int, ActionStarFriend] = (id1: Int) => {
    new ActionStarFriend {
      val `type` = STAR_FRIEND
      val id = id1
    }
  } 
  
  val initFriend: js.Function0[GenericAction] = () => {
    new GenericAction {
      val `type` = INIT_FRIEND
      
    }
  }

}
 
 

 
 