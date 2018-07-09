import ru.atol.drivers10.fptr.Fptr;
import ru.atol.drivers10.fptr.IFptr;

import java.util.Date;

public class Begin {
    private static IFptr fptr = new Fptr();


    public static void main(String[] args) {
        fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_MODEL, String.valueOf(IFptr.LIBFPTR_MODEL_ATOL_22F));
        fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_PORT, String.valueOf(IFptr.LIBFPTR_PORT_TCPIP));
        fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_IPADDRESS, "192.168.6.105");
        //fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_PORT, "5555");
        fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_OFD_CHANNEL, String.valueOf(IFptr.LIBFPTR_OFD_CHANNEL_NONE));
        fptr.applySingleSettings();
        fptr.open();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (fptr.printText() < 0) {
            System.out.println(String.format("%d [%s]", fptr.errorCode(), fptr.errorDescription()));
        }

        fptr.setParam(IFptr.LIBFPTR_PARAM_DATA_TYPE, IFptr.LIBFPTR_DT_STATUS);
        fptr.queryData();

        boolean isOpened = fptr.isOpened();
        System.out.println(isOpened);
        Date dateTime = fptr.getParamDateTime(IFptr.LIBFPTR_PARAM_DATE_TIME);
        System.out.println(dateTime);

        String serialNumber = fptr.getParamString(IFptr.LIBFPTR_PARAM_SERIAL_NUMBER);
        String modelName       = fptr.getParamString(IFptr.LIBFPTR_PARAM_MODEL_NAME);
        long shiftNumber   = fptr.getParamInt(IFptr.LIBFPTR_PARAM_SHIFT_NUMBER);

        System.out.println(serialNumber);
        System.out.println(modelName);
        System.out.println("Номер смены: " + shiftNumber);

        fptr.printCliche();
        fptr.cut();
        fptr.beep();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fptr.destroy();
    }
}
