package com.dainc.utils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.dainc.model.MstUserModel;

import jp.co.nobworks.openfunxion4.core.BlockLayout;
import jp.co.nobworks.openfunxion4.core.Box;
import jp.co.nobworks.openfunxion4.core.Line;
import jp.co.nobworks.openfunxion4.core.OpenFunXion;
import jp.co.nobworks.openfunxion4.core.OpenFunXionException;
import jp.co.nobworks.openfunxion4.core.Text;
/*
 * �o�[�R�[�h�t�H���g�� http://itext.sourceforge.net/downloads/barcodefonts.zip ���擾<BR>
 * �T���v���v���O�����̓f�o�b�O���[�h���n�m�ɂ��Ă��܂��B�i�f�U�C���c�[���̐ݒ�Ŏw��j
 * �f�o�b�O���[�h�ɂ��邱�ƂŁA�c�[����ł̊m�F�Ɠ��l�̓�������܂��B<BR>
 * �i�m�F�p��ƃt�@�C���̎g�p�A�m�F�p�R�}���h�̎��s�j
 */


public class ReportUtil {
	
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("ReportFileLink");	//�T�[�o�[��xml��PDF �t�@�C���̃����N
    
    public static void exec(List<MstUserModel> dataList) {
        OpenFunXion ofx = new OpenFunXion( resourceBundle.getString("file_xml") );                        // ���[���XML�t�@�C���̓ǂݍ���
        try {                                                   
            ofx.open( resourceBundle.getString("file_pdf") );                                                           // �o��PDF�t�@�C���̃I�[�v��
        } catch ( IOException e ) {
            e.printStackTrace();   
            return;
        } catch ( OpenFunXionException e ) {
            e.printStackTrace();   
            return;
        }
        makePdf( ofx, dataList );                                                                        // PDF�t�@�C���ւ̏o�͏���
    }
   
    public static  void makePdf( OpenFunXion ofx, List<MstUserModel> dataList) {
        
    	MstUserModel userModel = dataList.get(0);
        int pageTotal = (dataList.size()+15)/16;
        // ���C�A�E�g�̌Œ蕔���o��
        printOutline( ofx,userModel.getMstRoleModel().getAuthorityName() );
        
        // Y�����̈ړ��ʂ����߂�
        int moveY = 40;
        
        int pageNo = 1;
//        // �y�[�W�����擾
        Text page = ofx.getText( "text_13" );
//        // �����y�[�W���̐ݒ�
        page.setMessage( "PAGE: " + pageNo +"/"+ pageTotal );
//        // �y�[�W���̏o��
        page.print();
        
        // ���y�[�W�p�̃J�E���^
        int count = 0;
        
        // �f�U�C���c�[���ō쐬�����e�ꗗ���ڂ̎擾
        Line rowLine = ofx.getLine( "row_line" );
        Text userId = ofx.getText( "row_user" );
        Text name = ofx.getText( "row_name" );
        Text sex = ofx.getText( "row_sex" );
        Text age = ofx.getText( "row_age" );
        Text no = ofx.getText("row_no");

        // ��L�� ���ꂼ��̌^�ɂ��킹�����\�b�h�Ŏ擾���Ă��܂���
        // ����̓L���X�g���Ď擾�����
        Box reverseRow = (Box)ofx.getPrintObject( "reverse_row" );
        
        BlockLayout dataBlock = ofx.getBlockLayout( "data_block" );
        for ( Iterator it=dataList.iterator();it.hasNext(); ) {
            MstUserModel model = (MstUserModel)it.next();
            
            if ( count > 0 && count % 16 == 0 ) {
                // ���y�[�W
                ofx.newPage();
                // ���y�[�W�����̂ŁA�ʒu�����ɖ߂�
 
                // BlockLayout �Ŏw�肷��ƊȒP
                dataBlock.resetPosition();
                
                // �V�����y�[�W�̌Œ蕔���o��
                printOutline( ofx,userModel.getMstRoleModel().getAuthorityName() );
                pageNo++;
                page.setMessage( "PAGE: " + pageNo +"/"+ pageTotal );
                page.print();
            }
            
            no.setMessage(String.valueOf(count+1));
            no.print();
            no.moveY( moveY );
            
            userId.setMessage( model.getUserId() );
            userId.print();
            userId.moveY( moveY );
            
            name.setMessage( model.getFirstName()+" "+model.getFamilyName() );
            name.print();
            name.moveY( moveY );
            

  
            if(model.getMstGenderModel().getGenderName()!=null) {
            	sex.setMessage(String.valueOf(model.getMstGenderModel().getGenderName()));}
            else {
            	sex.setMessage("");
            }
            sex.print();
            sex.moveY( moveY );
            
            if(model.getAge()!=0) {
            	age.setMessage(String.valueOf(model.getAge()));}
            else {
            	age.setMessage("");
            }
            age.print();
            age.moveY( moveY );
                        
            rowLine.print();
            rowLine.moveY( moveY );
            
            count++;
        }
        // �I������
        ofx.out();
    }
    /**
     * �O�ϕ����̏o��
     */
    public static void printOutline( OpenFunXion ofx, String roleName ) {
        ofx.print( "body_block" );
        ofx.print( "title_1" );
        ofx.print( "header_box" );
        ofx.print( "header_1" );
        ofx.print( "header_2" );
        ofx.print( "header_3" );
        ofx.print( "header_4" );
        ofx.print( "header_5" );
        ofx.print( "out_box" );
        Text role =ofx.getText("text_15");
        Text time =ofx.getText("text_12");
        String timeStamp = new SimpleDateFormat("yyyy/M/dd HH:mm:ss").format(new Date());
        time.setMessage(timeStamp);
        time.print();
        role.setMessage(roleName);
        role.print();
    }
    
    

}