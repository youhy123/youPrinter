package org.apache.cordova.youprinter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;
import android.os.IBinder;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import net.posprinter.posprinterface.IMyBinder;
import net.posprinter.posprinterface.ProcessData;
import net.posprinter.posprinterface.TaskCallback;
import net.posprinter.service.PosprinterService;
import net.posprinter.utils.DataForSendToPrinterPos80;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
/**
 * This class echoes a string called from JavaScript.
 */
public class youPrinter extends CordovaPlugin {
public static IMyBinder myBinder;
public static boolean ISCONNECT=false;
private Activity activity;
private Context context;
    ServiceConnection mSerconnection= new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service){
            myBinder=(IMyBinder)service;
            // Log.e( tag:"myBinder",msg:"connect");
        }
        @Override
        public void onServiceDisconnected(ComponentName name){
            // Log.e( tag:"myBinder",msg:"disconnect");
        }
    };

    @Override
    public boolean execute(String action, JSONArray args,CallbackContext callbackContext) throws JSONException {
        boolean result = true;
        switch (action) {
            case "created":
                activity = cordova.getActivity();
                Intent intent =new Intent(activity, PosprinterService.class);
                activity.bindService(intent,mSerconnection,Context.BIND_AUTO_CREATE);
                break;
            case "connectNet":
                String ipAdrress = args.getString(0);
                connectNet(ipAdrress,callbackContext);
                break;
            case "disConnect":
                disConnect(callbackContext);
                break;
            case "printQddSample":
                CardBean cardBean = com.alibaba.fastjson.JSONObject.parseObject(args.getString(0), CardBean.class);
                printQddSample(cardBean,cardBean.getConsumeMaterialList(),callbackContext);
                break;
            default:
                result = false;    
        }        
        return result;
    }
     /**
     * 网络连接
     */
    private void connectNet(String ipAdrress,CallbackContext callbackContext){
        myBinder.ConnectNetPort(ipAdrress, 9100, new TaskCallback() {
            @Override
            public void OnSucceed() {
                ISCONNECT = true;
                callbackContext.success("连接成功");
            }

            @Override
            public void OnFailed() {
                ISCONNECT = false;
                callbackContext.error("Expected one non-empty string argument.");
                disConnect(callbackContext);
                return;
            }
        });

    }
    /**
     * 断开连接
     */
    private void disConnect(CallbackContext callbackContext){
        if (ISCONNECT){
            myBinder.DisconnectCurrentPort(new TaskCallback() {
                @Override
                public void OnSucceed() {
                    ISCONNECT = false;
                    callbackContext.success("已断开连接");
                }

                @Override
                public void OnFailed() {
                    ISCONNECT = true;
                    callbackContext.error("Expected one non-empty string argument.");
                }
            });
        }
    }
    /**
     * 打印千代达模板
     */
    public void printQddSample(final CardBean cardBean, final List<BomBean> bomBeanList,CallbackContext callbackContext) {
        try {
            if (ISCONNECT){
                myBinder.WriteSendData(new TaskCallback() {
                    @Override
                    public void OnSucceed() {
                        disConnect(callbackContext);
                        callbackContext.success("打印成功");
                    }

                    @Override
                    public void OnFailed() {
                        disConnect(callbackContext);
                        callbackContext.success("打印失败");
                        return;
                    }
                }, new ProcessData() {
                    @Override
                    public List<byte[]> processDataBeforeSend() {
                        List<byte[]> list = new ArrayList<>();
                        //打个空行
                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        //选择对齐方式
                        list.add(DataForSendToPrinterPos80.selectAlignment(0));
                        //选择HRI文字文字 不传就不显示文本内容
                        //list.add(DataForSendToPrinterPos80.selectHRICharacterPrintPosition(0));
                        //设置条码宽度
                        list.add(DataForSendToPrinterPos80.setBarcodeWidth(2));
                        //设置高度
                        list.add(DataForSendToPrinterPos80.setBarcodeHeight(80));
                        //条码的类型和内容，73是code128的类型，请参考说明手册每种类型的规则
                        //WO号码 条码,  （工单号码）
                        String strBarcode=StringUtils.setEmptyString(cardBean.getProductionOrderNumber());
                        list.add(DataForSendToPrinterPos80.printBarcode(73,13,"{B"+strBarcode));
                        //打印指令
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());


                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        list.add(DataForSendToPrinterPos80.selectCharacterSize(1));//加大字符大小
                        /*list.add(DataForSendToPrinterPos80.selectAlignment(0));*/
                        list.add(net.posprinter.utils.StringUtils.strTobytes(strBarcode));//工作单号码
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(100,01));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        list.add(DataForSendToPrinterPos80.selectCharacterSize(1));//加大字符大小
                        /*list.add(DataForSendToPrinterPos80.selectAlignment(2));*/
                        list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(cardBean.getOperationNumber())));//工序代码
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        //打个空行
                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        list.add(DataForSendToPrinterPos80.selectCharacterSize(1));//加大字符大小
                        /*list.add(DataForSendToPrinterPos80.selectAlignment(0));*/
                        list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(cardBean.getProductName())));// 图番
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(100,01));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        list.add(DataForSendToPrinterPos80.selectCharacterSize(2));//加大字符大小
                        /*list.add(DataForSendToPrinterPos80.selectAlignment(1));*/
                        list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(cardBean.getProductVersion())));//版次
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());



                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(net.posprinter.utils.StringUtils.strTobytes("QTY："));
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(30,00));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        //list.add(DataForSendToPrinterPos80.selectAlignment(0));//居左
                        String PlanQuantity="";
                        if(cardBean.getPlanQuantity()!=null&&cardBean.getPlanQuantity().contains(".")) {
                            int indexOf = cardBean.getPlanQuantity().indexOf(".");
                            PlanQuantity = cardBean.getPlanQuantity().substring(0, indexOf);
                        }
                        //list.add(net.posprinter.utils.StringUtils.strTobytes(cardBean.getActualQuantity()+"/"+StringUtils.setEmptyString(PlanQuantity)));//报工数量/订单总数量
                        list.add(net.posprinter.utils.StringUtils.strTobytes(cardBean.getActualQuantity()+"/"+NumberUtils.delZero(cardBean.getPlanQuantity())));//报工数量/订单总数量
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(40,01));
                        list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));//加粗
                        //list.add(DataForSendToPrinterPos80.selectAlignment(2));//居右
                        list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(cardBean.getEquipmentNumber())));//报工机台代码
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        //list.add(DataForSendToPrinterPos80.selectAlignment(0));//居左
                        Calendar calendar= Calendar.getInstance();
                        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                        list.add(net.posprinter.utils.StringUtils.strTobytes(dateFormat.format(calendar.getTime())));//打印时间

                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(80,01));
                        //list.add(DataForSendToPrinterPos80.selectAlignment(2));
                        list.add(net.posprinter.utils.StringUtils.strTobytes(cardBean.getOperator()));// 工号
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        //打个空行
                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10,00));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        if (!ListUtils.isEmpty(bomBeanList)) {
                            //*注：只打印本工序投入原材料，如果本工序未投入原材料，则为空
                            for (int i = 0; i < bomBeanList.size(); i++) {
                                BomBean bomBean = bomBeanList.get(i);
                                list.add(DataForSendToPrinterPos80.initializePrinter());
                                list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10, 00));
                                //list.add(DataForSendToPrinterPos80.selectAlignment(0));//居左
                                int ii=i+1;
                                list.add(net.posprinter.utils.StringUtils.strTobytes(ii+"."+ StringUtils.setEmptyString(bomBean.getMaterialDesc())));//内容：材料名称
                                list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(100, 01));
                                //list.add(DataForSendToPrinterPos80.selectAlignment(2));
                                //list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(bomBean.getMaterialVersionNumber())));// 版次
                                list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(bomBean.getVersionNumber())));// 版次 20220817 modify by lzw
                                list.add(DataForSendToPrinterPos80.printAndFeedLine());

                                list.add(DataForSendToPrinterPos80.initializePrinter());
                                list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(20, 00));
                                //list.add(DataForSendToPrinterPos80.selectAlignment(0));//居左
                                //list.add(net.posprinter.utils.StringUtils.strTobytes(NumberUtils.delZero(bomBean.getSpecification()))); //物料规格
                                // list.add(net.posprinter.utils.StringUtils.strTobytes(NumberUtils.delZero(bomBean.getCutSizeY()) + " * " + NumberUtils.delZero(bomBean.getCutSizeX())));//截断尺寸
                                list.add(net.posprinter.utils.StringUtils.strTobytes(StringUtils.setEmptyString(bomBean.getCutSize())));//卷宽

                                /*list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(40, 01));
                                list.add(net.posprinter.utils.StringUtils.strTobytes("QTY:"));
                                list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(100, 01));
                                list.add(DataForSendToPrinterPos80.selectAlignment(2));
                                list.add(net.posprinter.utils.StringUtils.strTobytes(NumberUtils.delZero(bomBean.getActualQuantity())));//投入的数量
                                list.add(DataForSendToPrinterPos80.printAndFeedLine());*/
                                list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(40, 01));
                                //list.add(DataForSendToPrinterPos80.selectAlignment(2));
                                list.add(net.posprinter.utils.StringUtils.strTobytes("QTY:"+NumberUtils.delZero(bomBean.getActualQuantity())));//投入的数量
                                list.add(DataForSendToPrinterPos80.printAndFeedLine());
                            }
                        }
                        //打个空行
                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(10, 00));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(00,00));
                        list.add(DataForSendToPrinterPos80.selectAlignment(1));
                        list.add(net.posprinter.utils.StringUtils.strTobytes("---------------------end---------------------"));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());

                        list.add(DataForSendToPrinterPos80.initializePrinter());
                        list.add(DataForSendToPrinterPos80.setAbsolutePrintPosition(00,00));
                        list.add(DataForSendToPrinterPos80.selectAlignment(1));
                        list.add(net.posprinter.utils.StringUtils.strTobytes(cardBean.getWenyan()));
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.printAndFeedLine());
                        list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(1));//选择切纸模式并切纸
                        return list;
                    }
                });

            }else {
                disConnect(callbackContext);
                callbackContext.success("打印连接失败");
            }

        } catch (Exception e) {
            callbackContext.success("打印异常");
            disConnect(callbackContext);
        }
    }
}
