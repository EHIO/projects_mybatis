package db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ffzx.commerce.framework.utils.DateUtil;
import com.mysql.jdbc.StringUtils;

public class CreateDto {

	private static boolean haveDate;
	public static void main(String[] args) {
		create("cims_db", "commodity_ext_attr","com.ffzx.commodity.model","DataEntity","调价申请","wg","F:\\Workspace\\cims\\cims-core");
	}
	
	public static void create(String dbName,String tableName,String packageName,String superClass,String comment,String author,String path){
		List<DataModel> list = getDataModel(dbName, tableName);
		String members = getMembers(list);
		String className = format(tableName);
		className = StringUtils.firstAlphaCharUc(className, 0) + className.substring(1);
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("package ").append(packageName).append(";\n\n");
		sbf.append("import com.ffzx.commerce.framework.common.persistence.").append(superClass).append(";\n");
		sbf.append("import javax.validation.constraints.NotNull;").append("\n");
		sbf.append("import org.hibernate.validator.constraints.Length;").append("\n");
		if(haveDate)
			sbf.append("import java.util.Date;").append("\n\n");
		sbf.append("/**").append(comment).append("\n * @author ").append(author).append("\n * @date ").append(DateUtil.format(new Date(), "yyyy-MM-dd")).append("\n * @version 1.0").append("").append("\n */\n");
		sbf.append("public class ").append(className).append(" extends ").append(superClass).append("<").append(className).append(">").append("{").append("\n")
		   .append(members).append("}");
		System.out.println(sbf.toString());
		createFile(path, packageName,className, sbf.toString());
	}
	
	private static void createFile(String path,String packageName,String className,String content){
		try {
			String dir = path + "/" + "src/main/java/";
			dir += packageName.replaceAll("\\.", "/");
			File fileDir = new File(dir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			File file = new File(dir + "/" + className+ ".java");
			if(file.exists()){
				System.out.println("Dto文件己存在");
				return;
			}
			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.close();
			System.out.println("Dto created...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String format(String t){
		t = t.toLowerCase();
		String rt = "";
		if(t.contains("_")){
			String strs[] = t.split("_");
			for (int i = 0; i < strs.length; i++) {
				String str = strs[i];
				if(i>0){
					str = StringUtils.firstAlphaCharUc(str, 0) + str.substring(1);
				}
				rt += str;
			}
		}else{
			rt = t;
		}
		return rt;
	}
	
	private static String getMembers(List<DataModel> list){
		StringBuffer sbf = new StringBuffer("\tprivate static final long serialVersionUID = 1L;\n");
		StringBuffer sbfMethod = new StringBuffer();
		for (DataModel dataModel : list) {
			String ptName = format(dataModel.getColumnName());
			if(ptName.equals("id") || ptName.equals("remarks") || ptName.equals("createBy") || ptName.equals("createDate")
					 || ptName.equals("lastUpdateBy") || ptName.equals("lastUpdateDate") || ptName.equals("delFlag"))
				continue;
			String type = "";
			switch (dataModel.getDataType()) {
				case "varchar":
					type = "String";
					break;
				case "char":
					type = "String";
					break;
				case "text":
					type = "String";
					break;
				case "decimal":
					type = "Double";
					break;
				case "timestamp":
					type = "Date";
					haveDate = true;
					break;
				case "date":
					type = "Date";
					break;
				case "int":
					type = "Integer";
					break;
				default:
					type = "String";
					break;
			}
			
			sbf.append("\t").append("/** ").append(StringUtils.isNullOrEmpty(dataModel.getColumnComment())?"":dataModel.getColumnComment()).append("*/");
			if(dataModel.getIsNullable().equals("NO"))
				sbf.append("\n").append("\t").append("@NotNull").append("(message=\"").append(StringUtils.isNullOrEmpty(dataModel.getColumnComment())?"":dataModel.getColumnComment()).append("不能为空").append("\")");
			if(dataModel.getCharacterMaximumLength() !=null)
				sbf.append("\n").append("\t").append("@Length(min=0, max=").append(dataModel.getCharacterMaximumLength()).append(")");
			sbf.append("\n").append("\t").append("private ").append(type).append(" ").append(ptName).append(";").append("\n\n");
			
			sbfMethod.append("\t").append("public ").append(type).append(" ").append("get").append(StringUtils.firstAlphaCharUc(ptName, 0) + ptName.substring(1))
					 .append("(){").append("\n").append("\t\t").append("return ").append(ptName).append(";").append("\n").append("\t").append("}")
					 .append("\n\n")
					 .append("\t").append("public void ").append("set").append(StringUtils.firstAlphaCharUc(ptName, 0) + ptName.substring(1))
					 .append("(").append(type).append(" ").append(ptName).append("){").append("\n")
					 .append("\t\t").append("this.").append(ptName).append(" = ").append(ptName).append(";").append("\n").append("\t").append("}")
					 .append("\n\n");
		}
		return sbf.append(sbfMethod).toString();
	}
	private static List<DataModel> getDataModel(String dbName,String tableName){
		List<DataModel> list = new ArrayList<>();
		String sql = "select COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,COLUMN_COMMENT from columns where table_name=? AND TABLE_SCHEMA = ?";//SQL语句
		DBConnection db = new DBConnection(sql);
        try {  
        	PreparedStatement pst = db.pst;
        	pst.setString(1, tableName);
        	pst.setString(2, dbName);
        	ResultSet rs = db.pst.executeQuery();//执行语句，得到结果集  
            while (rs.next()) {  
            	DataModel dm = new DataModel();
            	dm.setColumnName(rs.getString(1));
            	dm.setIsNullable(rs.getString(2));
            	dm.setDataType(rs.getString(3));
            	dm.setCharacterMaximumLength(rs.getString(4));
            	dm.setColumnComment(rs.getString(5));
            	
            	list.add(dm);
            }//显示数据  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	db.close();
        }
		
		return list;
	}
	 
}
