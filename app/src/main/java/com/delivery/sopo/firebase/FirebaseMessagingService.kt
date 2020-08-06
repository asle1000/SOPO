package com.tcodevice.card.tada.firebase

import android.content.Intent
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject

/**
 * <h1>Firebase Push NotificationConst Service</h1>
 * Push Notification이 도착했을 때 처리하는 Class
 *
 * @author Kyungyoon Kim
 * @version 1.1.0
 * @since 2018.09.12
 */
class FirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService()
{
//    private val logger by getLogger()
//    private val userRepository: UserRepository by inject()
//    private val moduleRepository: ModuleRepository by inject()
//
//    override fun onNewToken(newToken: String)
//    {
//        try
//        {
//            // 업데이트 된 Token을 불러온다.
//            logger.info("새로운 Token : $newToken")
//
//            //서버에 보고하여 업데이트 한다.
//            updateToken(userRepository, moduleRepository, newToken)
//        }
//        catch (e: Exception)
//        {
//            logger.error("onNewToken + ${e.toString()}")
//        }
//    }
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage)
//    {
//        try
//        {
//            if (remoteMessage.data.isNotEmpty())
//            {
//                logger.debug("Message data payload: ${remoteMessage.data}")
//
//                val encrypted = remoteMessage.data["message"]
//                if (encrypted != null && encrypted.isNotEmpty())
//                {
//                    moduleRepository.processPushMessage(encrypted){
//                            result,
//                            error,
//                            errorMessage ->
//
//                        if(error != null) {
//                            logger.error("FCM Process fail with code [$error]\n\r$errorMessage")
//                        }
//                        else
//                        {
//                            when(result!!.fcmType)
//                            {
//                                FCMType.CardCharge ->
//                                {
//                                    val data = result.getData(FCMChargeResult::class.java)
//
//                                    val noti = EzNotification(applicationContext,  NotificationConst.FIREBASE_CHARGE)
//                                    noti.setIcon(R.drawable.ic_img_bank_charge)
//                                    noti.setTitle("삨NFC(BBIK) 가상계좌 입금 확인")
//                                    noti.setMessage("가상계좌에 " + data.chargeAmount + "원이 입금되었습니다.\n앱을 실행하여 잔액을 새로고침 해주세요!")
//                                    noti.setIntent(SplashActivity::class.java)
//                                    noti.setVisibility(VISIBILITY_PUBLIC)
//                                    noti.setNotiToUser(true)
//                                    noti.setWakeUp()
//                                    noti.show()
//
//                                    //처리 완료 알리기 (UI 업데이트)
//                                    val intent = Intent(BroadcastConst.BBIK_HCE_FINISHED)
//                                    this.sendBroadcast(intent)
//                                }
//
//                                FCMType.CardRefund ->
//                                {
//                                    val data = result.getData(FCMRefundResult::class.java)
//
//                                    val noti = EzNotification(applicationContext, NotificationConst.FIREBASE_CHARGE)
//                                    noti.setIcon(R.drawable.ic_img_bank_charge)
//                                    noti.setTitle("삨NFC(BBIK) 충전 취소 완료")
//                                    noti.setMessage("가상계좌 입금을 통해 충전 하셨던 " + data.refundAmount + "원이 충전 취소되었습니다.")
//                                    noti.setIntent(DrawerActivity::class.java)
//                                    noti.setVisibility(VISIBILITY_PUBLIC)
//                                    noti.setNotiToUser(true)
//                                    noti.setWakeUp()
//                                    noti.show()
//
//                                    //처리 완료 알리기 (UI 업데이트)
//                                    val intent = Intent(BroadcastConst.BBIK_HCE_FINISHED)
//                                    this.sendBroadcast(intent)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (remoteMessage.notification != null) {
//                logger.debug("Message NotificationConst Body: ${remoteMessage.notification!!.body}")
//
//            }
//        }
//        catch (e: Exception) {
//            logger.error("onMessageReceived : ${e.toString()}")
//        }
//
//    }
//
//    companion object
//    {
//        private val logger by getLogger()
//
//        fun forceTokenUpdate(userRepository: UserRepository, moduleRepository: ModuleRepository)
//        {
//            try
//            {
//                userRepository.setFirebaseTokenisUpdated(false)
//                checkTokenUpdated(userRepository, moduleRepository)
//            }
//            catch (e: Exception) {
//                logger.error("error : ${e.toString()}")
//            }
//        }
//
//        fun updateToken(userRepository: UserRepository, moduleRepo: ModuleRepository, newToken: String)
//        {
//            try
//            {
//                //발급된 교통카드가 없을 경우 false 리턴한다.
//                if(!moduleRepo.isRegistered()) return
//
//                //저장 되어있는 토큰과 새로 읽어온 토큰이 같고, 이미 업데이트를 한 상태이면 프로세스를 종료한다.
//                val isUpdated = userRepository.getFirebaseTokenUpdated()
//                val savedToken = userRepository.getFirebaseToken()
//                if (savedToken == newToken && isUpdated) return
//
//                //만약 다르거나, 이전에 업데이트를 실패했었으면, 토큰을 업데이트 한다.
//                logger.info("Token 업데이트 시도 : $newToken")
//
//                //토큰 정보를 저장한다.
//                userRepository.setFirebaseToken(newToken)
//                userRepository.setFirebaseTokenisUpdated(false)
//
//                //서버로 업데이트를 요청한다.
//                moduleRepo.updateFirebaseToken(newToken){
//                        accountNumber,
//                        error,
//                        errorMessage ->
//
//                    if (accountNumber == null) {
//                        logger.error("토큰 업데이트 실패 $error\n\r$errorMessage")
//                    }
//                    else
//                    {
//                        userRepository.setAccountNumber(accountNumber)
//                        userRepository.setFirebaseTokenisUpdated(true)
//                        logger.info("토큰 업데이트 성공 $accountNumber")
//                    }
//                }
//            }
//            catch (e:Exception) {
//                logger.error("updateToken() $e")
//            }
//        }
//
//        fun checkTokenUpdated(userRepository: UserRepository, moduleRepo: ModuleRepository)
//        {
//            try {
//                //발급된 교통카드가 없을 경우 false 리턴한다.
//                if(!moduleRepo.isRegistered()) return
//
//                FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
//                    val deviceToken = instanceIdResult.token
//                    updateToken(userRepository, moduleRepo, deviceToken)
//                }
//            }
//            catch (e: Exception) {
//                logger.error("checkTokenUpdated() $e")
//            }
//        }
//    }
}