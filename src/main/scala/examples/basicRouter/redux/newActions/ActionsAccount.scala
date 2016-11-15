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