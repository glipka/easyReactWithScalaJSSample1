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
class AccountData(val firstName:String,val lastName:String,val age:Int, val sex:String,val employed:Boolean,val favoriteIco:String,val bio:String) extends js.Object  
  

@ScalaJSDefined
trait ActionAccountLoad extends js.Object with GenericAction {
   val accountData : AccountData
}
@ScalaJSDefined
object ActionsAccount extends js.Object  {
  
   val loadAccount: js.Function1[AccountData,ActionAccountLoad] = (data: AccountData) => {
    new ActionAccountLoad {
      val `type` = ACCOUNT_LOAD
      val accountData =  data
    }
  }

}