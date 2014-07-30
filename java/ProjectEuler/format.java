public class format{
    public String CreateExtractExpert(Long packageId,Long committeeMainId){
        try {
            String srcPath="bidEvaFile"+ FileUtil.separator +"tableTemplate"+FileUtil.separator +"extract_expert_temp.xls";
            String DescFile=AppConfig.getProperty("Location_Default_Dir")+FileUtil.separator +"bidEvaFile"+ FileUtil.separator +"tableTemplate";
            File targetFile = FileUtil.getFile(srcPath);
            HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(targetFile));
            HSSFSheet sheet=hssfWorkbook.getSheetAt(0);


            List<QueryCreateCommitteeModel> createExpert=dao.queryExtractExpert(committeeMainId);
            if(createExpert!=null && createExpert.size() > 0){
                HSSFRow row3=sheet.getRow(1);
                row3.getCell(1).setCellValue(createExpert.get(0).getTendereeName());
                HSSFCellStyle style=row3.getCell(1).getCellStyle();
                row3.getCell(3).setCellValue(createExpert.get(0).getAgentName());
                row3.getCell(3).setCellStyle(style);
                row3.createCell(5).setCellValue(WebUtil.getShiroUser().getTrueName());
                row3.getCell(5).setCellStyle(style);


                HSSFRow row4=sheet.getRow(2);
                row4.getCell(1).setCellValue(createExpert.get(0).getProjectName());
                row4.getCell(3).setCellValue(createExpert.get(0).getBidCode());
                row4.getCell(3).setCellStyle(style);

                row4.createCell(5).setCellValue("国电招投标网和中国采购与招标网");
                row4.getCell(5).setCellStyle(style);

                HSSFRow row5=sheet.getRow(3);
                //SimpleDateFormat  format = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
                if(null!=createExpert.get(0).getPublishTime()){
                    row5.getCell(1).setCellValue(createExpert.get(0).getPublishTime());
                }else{
                    row5.getCell(1).setCellValue("");
                }

                row5.getCell(1).setCellStyle(style);
                row5.getCell(3).setCellValue(createExpert.get(0).getOpenTime());
                row5.getCell(3).setCellStyle(style);
                row5.createCell(5).setCellValue(createExpert.get(0).getEvaluationPlace());
                row5.getCell(5).setCellStyle(style);

                HSSFRow row6=sheet.getRow(4);
                row6.getCell(1).setCellValue(createExpert.get(0).getTendereeLinkMan());
                row6.getCell(1).setCellStyle(style);
                row6.getCell(3).setCellValue(createExpert.get(0).getTendereeLinkJob());
                row6.getCell(3).setCellStyle(style);
                row6.createCell(5).setCellValue(createExpert.get(0).getTendereeLinkMobile());
                row6.getCell(5).setCellStyle(style);

                HSSFRow row7=sheet.getRow(5);
                row7.getCell(1).setCellValue(createExpert.get(0).getTendereeLinkTel());
                row7.getCell(1).setCellStyle(style);
                row7.getCell(3).setCellValue(createExpert.get(0).getTendereeLinkFax());
                row7.getCell(3).setCellStyle(style);
                row7.createCell(5).setCellValue(createExpert.get(0).getTendereeLinkEmail());
                row7.getCell(5).setCellStyle(style);

                HSSFRow row8=sheet.getRow(6);
                row8.getCell(1).setCellValue(createExpert.get(0).getAgentLinkMan());
                row8.getCell(1).setCellStyle(style);
                row8.getCell(3).setCellValue(createExpert.get(0).getAgentLinkJob());
                row8.getCell(3).setCellStyle(style);
                row8.createCell(5).setCellValue(createExpert.get(0).getAgentLinkMobile());
                row8.getCell(5).setCellStyle(style);

                HSSFRow row9=sheet.getRow(7);
                row9.getCell(1).setCellValue(createExpert.get(0).getAgentLinkTel());
                row9.getCell(1).setCellStyle(style);
                row9.getCell(3).setCellValue(createExpert.get(0).getAgentLinkFax());
                row9.getCell(3).setCellStyle(style);
                row9.createCell(5).setCellValue(createExpert.get(0).getAgentLinkEmail());
                row9.getCell(5).setCellStyle(style);

                //评委会主表
                BpbCommitteeMain  bcm = (BpbCommitteeMain)dao.queryById2(committeeMainId, BpbCommitteeMain.class);
                //获取分包
                List packagelist = dao.getPackList(bcm.getPackageIds());
                //抽取申请
                LzjExtractionApplication lea =(LzjExtractionApplication)dao.querylzjExtractionApplicationInfoY(committeeMainId);
                //招标项目
                BidMain  bm = (BidMain)dao.queryById2(bcm.getBidId(), BidMain.class);
                //抽取专业
                String specialityIds[] =lea.getSpecialty().split(",");
                String sp="";
                String sp_rs="";
                for(int i = 0;i < specialityIds.length;i++) {
                    String specialtyCode = specialityIds[i];
                    if(sp.equals("")){
                        sp=getSpercialtyFullName(specialityIds[i]);
                        sp_rs=getSpercialtyFullName(specialityIds[i])+"  抽取"+lea.getSpecialtyExpertNum().split(",")[i]+"人";
                    }
                    else{
                        sp=sp+";"+getSpercialtyFullName(specialityIds[i]);
                        sp_rs=sp_rs+";"+getSpercialtyFullName(specialityIds[i])+" 抽取"+lea.getSpecialtyExpertNum().split(",")[i]+"人";
                    }
                }
                //抽取地域
                List areaNamesList = dao.queryAreaNamesList(lea.getArea());
                String areaNames="";
                for (int i=0;i<areaNamesList.size();i++){
                    QueryAreaClassModel qcm = (QueryAreaClassModel)areaNamesList.get(i);
                    if(areaNames.equals("")){
                        areaNames=qcm.getValDescCn();
                    }
                    else{
                        areaNames=areaNames+","+qcm.getValDescCn();
                    }
                }
                //所属专业
                String sql = "select t.val_desc_cn as \"name\" from t_pfm_base_data t where t.Value_Set_Code='engineeringCategory' and t.val='"+bm.getBidClass()+"'";
                String bidType = dao.getStrBySql(sql);

                for (int i = 0; i < packagelist.size(); i++) {
                    HSSFRow row11=sheet.createRow(9+i);
                    row11.setHeight((short)1250);
                    BidPackage bp=(BidPackage)packagelist.get(i);
                    row11.createCell(0).setCellValue(bp.getPackageCode());
                    row11.getCell(0).setCellStyle(style);
                    row11.createCell(1).setCellValue(bp.getPackageName());
                    row11.getCell(1).setCellStyle(style);
                    row11.createCell(2).setCellValue(bidType);
                    row11.getCell(2).setCellStyle(style);
                    row11.createCell(3).setCellValue(sp);
                    row11.getCell(3).setCellStyle(style);
                    row11.createCell(4).setCellValue(areaNames);
                    row11.getCell(4).setCellStyle(style);
                    row11.createCell(5).setCellValue(sp_rs);
                    row11.getCell(5).setCellStyle(style);
                    row11.createCell(6).setCellValue("随机抽取");
                    row11.getCell(6).setCellStyle(style);
                }
                sheet.addMergedRegion(new Region(9,(short)2,8+packagelist.size(),(short)2));
                sheet.addMergedRegion(new Region(9,(short)3,8+packagelist.size(),(short)3));
                sheet.addMergedRegion(new Region(9,(short)4,8+packagelist.size(),(short)4));
                sheet.addMergedRegion(new Region(9,(short)5,8+packagelist.size(),(short)5));
                sheet.addMergedRegion(new Region(9,(short)6,8+packagelist.size(),(short)6));
                try{
                    java.io.File file = new java.io.File(targetFile.getAbsoluteFile()+"");
                    java.io.FileOutputStream _fos = new java.io.FileOutputStream(file);
                    hssfWorkbook.write(new java.io.FileOutputStream(file));
                    _fos.flush();
                    _fos.close();
                    return	FileUtil.uploadFile(targetFile.getAbsoluteFile(),DescFile);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}