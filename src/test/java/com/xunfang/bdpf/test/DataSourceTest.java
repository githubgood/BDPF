//package com.xunfang.bdpf.test;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.*;
//
//import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
//import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssembly;
//import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;
//import com.xunfang.bdpf.mllib.assembly.mapper.StandardizationAssemblyMapper;
//import com.xunfang.bdpf.mllib.assembly.mapper.StandardizationChildAssemblyMapper;
//import com.xunfang.bdpf.mllib.assembly.service.*;
//import com.xunfang.bdpf.mllib.assembly.vo.StandardizationAssemblyVo;
//import com.xunfang.bdpf.mllib.experiment.entity.Table;
//import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
//import com.xunfang.bdpf.mllib.experiment.entity.TableChildExample;
//import com.xunfang.bdpf.mllib.experiment.mapper.TableChildMapper;
//import com.xunfang.bdpf.mllib.util.InsertIntoDB;
//import org.apache.commons.lang.StringUtils;
//import org.apache.spark.SparkConf;
//import org.apache.spark.SparkContext;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.mllib.linalg.Matrix;
//import org.apache.spark.mllib.linalg.Vector;
//import org.apache.spark.mllib.linalg.Vectors;
//import org.apache.spark.mllib.linalg.distributed.RowMatrix;
//import org.apache.spark.rdd.RDD;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
//import com.xunfang.utils.JDBCUtil;
//import com.xunfang.utils.StringTools;
//
//public class DataSourceTest extends BaseTest{
//
//	@Autowired
//	DataSourceService dss;
//	@Autowired
//	FillMissingValuesAssemblyService fmvas;
//
//	@Test
//	public void isTableExist(){
//
//		boolean f = dss.isTableExist("test");
//		System.out.print(f);
//		dss.deleteTable("test");
//	}
//
//	@Test
//	public void testStringConcat(){
//		String str = "t1 int , t2 double";
//		String s = "id int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), ";
//		String st = s.concat(str);
//		String sql = "create table test ("+st+")";
//		System.out.println("--------------------------------sql = "+sql);
//		String column ="";
//		String text = "10,25.6,28,38.3";
//		String outStr ="";
//		String[] ts = text.split(",");
//		String[] strs = new  String[]{"Hello","java","world","dubbo","","zookeper"};
//		for(int i =0 ;i<strs.length;i++){
//			column += strs[i]+",";
//		}
//		column = column.substring(0, column.length()-1);
//		System.out.println("--------------------------------column = "+column);
//
//		for(int i =0;i<ts.length;i++){
//			String tr = ts[i];
//			if("".equals(tr.trim()))
//				continue;
//			outStr += "\""+tr+"\",";
//		}
//		//String sql2 = "exec p_skill "+"\""+s+"\"";
//		outStr = outStr.substring(0, outStr.length()-1);
//
//		//System.out.println("-----------------------------sql2 = "+sql2);
//		System.out.println("-----------------------------ts = "+ts.length);
//		System.out.println("-----------------------------outStr = "+outStr);
//	}
//
//	@Test
//	public void testStringTools(){
//			String uuid_1 = StringTools.getUUID();
//			String uuid_2 = StringTools.getUUID();
//			for(int i =0 ;i<1000;i++){
//
//				System.out.println(uuid_1 == uuid_2);
//			}
//
//	}
//
//	@Test
//	public void testColumnValue(){
//		Connection connection = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "";
//		String x = "";
//		try {
//			connection = JDBCUtil.getConnection();
//			sql = "SELECT * FROM test LIMIT 10";
//			ps = connection.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while(rs.next()){
//				 x += rs.getString("c1") +",";
//
//			}
//			System.out.println(x.substring(0, x.length()-1));
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}finally {
//			JDBCUtil.close(connection, rs, ps);
//		}
//
//	}
//
//	@Test
//	public void testDB(){
//		Table table = new Table();
//		table.setName("adult");
//		String columnValues ="age,workclass,fnlwgt,education,education_num,maritial_status,occupation,relationship,race,sex,capital_gain,capital_loss,hours_per_week,native_country,income";
//		Map<String,String> map1 = InsertIntoDB.queryMax("bdpf",table,columnValues,"root","tiger");
//		Map<String,String> map2 = InsertIntoDB.queryMin("bdpf",table,columnValues,"root","tiger");
//		System.out.println(map1);
//		System.out.println(map2);
//	}
//	@Test
//	public void testAvg(){
//		String columnValues ="age,workclass,fnlwgt,education,education_num,maritial_status,occupation,relationship,race,sex,capital_gain,capital_loss,hours_per_week,native_country,income";
//
//		//Map<String,String> map =  InsertIntoDB.queryAvg("adult",columnValues);
//		List<LinkedHashMap<String,Object>> mapList = InsertIntoDB.queryAll("adult",columnValues);
//		System.out.println(mapList);
//	}
//
//	@Test
//	public void testJoin(){
//		Connection connection = null;
//		PreparedStatement ps =null;
//		ResultSet rs = null;
//		LinkedHashMap<String,Object> map ;
//		List<LinkedHashMap<String,Object>> resultList = new ArrayList<>();
//		try {
//			connection =JDBCUtil.getConnection();
//			String sql ;
//			sql = "SELECT income,cp,education FROM adult a ,heart_disease_prediction b WHERE  a.age = b.age";
//			ps = connection.prepareStatement(sql);
//			rs = ps.executeQuery();
//			ResultSetMetaData rsma = rs.getMetaData();
//			int count = rsma.getColumnCount();
//			//字段名称
//			List<TableChild> columnNamelist = new ArrayList<TableChild>();
//			for (int i = 1; i <= count; i++) {
//				TableChild tableChild = new TableChild();
//				tableChild.setName(rsma.getColumnName(i));
//				if("VARCHAR".equals(rsma.getColumnTypeName(i))){
//					tableChild.setType("string");
//				}else{
//					tableChild.setType("string");
//				}
//				columnNamelist.add(tableChild);
//			}
//
//			//进行循环，取到所有的值
//			while(rs.next()){
//				map = new LinkedHashMap<>();
//				//遍历所有字段并放入到resultMap中
//				for (TableChild tableChilds : columnNamelist){
//					//字段类型判断
//					if("int".equals(tableChilds.getType())){//bigint类型
//						map.put(tableChilds.getName(), rs.getInt(tableChilds.getName()));
//					}else if("double".equals(tableChilds.getType())){//double类型
//						map.put(tableChilds.getName(), rs.getDouble(tableChilds.getName()));
//					}else if("decimal".equals(tableChilds.getType())){//decimal类型
//						map.put(tableChilds.getName(), rs.getBigDecimal(tableChilds.getName()));
//					}else if("string".equals(tableChilds.getType())){//string字符串
//						map.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
//					}else if("boolean".equals(tableChilds.getType())){//boolean类型
//						map.put(tableChilds.getName(), rs.getBoolean(tableChilds.getName()));
//					}else if("datetime".equals(tableChilds.getType())){//datetime 日期类型
//						map.put(tableChilds.getName(), rs.getTimestamp(tableChilds.getName()));
//					}else{//其它类型
//						map.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
//					}
//				}
//				//将当前resultMap放入resultList
//				resultList.add(map);
//			}
//			System.out.println(resultList.size());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCUtil.close(connection,rs,ps);
//		}
//	}
//	@Test
//	public void testDecimal(){
//		BigDecimal currentValue = new BigDecimal("12");
//		String result = currentValue.divide(new BigDecimal("20"),BigDecimal.ROUND_HALF_UP).toString();
//		System.out.println("------------------------------->"+result);
//	}
//
//	@Test
//	public void testPCA(){
//		String columnValues ="age,sex,cp,chol,fbs,oldpeak";
//		List<LinkedHashMap<String, Object>> resultList = InsertIntoDB.queryAll("heart_disease_prediction",columnValues);
//
//		List<String> keyList = new LinkedList<>();
//		LinkedHashMap<String,Object> m = resultList.get(0);
//		for (String key : m.keySet()){
//			keyList.add(key);
//		}
//
//		SparkConf conf = new SparkConf().setAppName("PCA_Example").setMaster("local[4]");
//		SparkContext sc = new SparkContext(conf);
//		LinkedList<Vector> rowsList = new LinkedList<Vector>();
//
//		for (LinkedHashMap<String,Object> e : resultList){
//			LinkedHashMap<String,Object> map = new LinkedHashMap<>();
//			double[] data = new double[keyList.size()];
//			for (int i = 0; i< keyList.size(); i++){
//				data[i] = Double.parseDouble(e.get(keyList.get(i)).toString());
//			}
//			Vector currentRow = Vectors.dense(data);
//			rowsList.add(currentRow);
//
//		}
//		JavaRDD<Vector> rows = JavaSparkContext.fromSparkContext(sc).parallelize(rowsList);
//		RowMatrix mat = new RowMatrix(rows.rdd());
//		Matrix pc = mat.computePrincipalComponents(2);
//		System.out.println(pc);
//		RowMatrix projected = mat.multiply(pc);//行矩阵乘法 -- 得到的新矩阵
//		RDD<Vector> d = projected.rows();
//		JavaRDD<Vector> dds = d.toJavaRDD();
//		List<Vector> sd = dds.collect();
//		List<double[]> o = new ArrayList<>();
//		System.out.println(sd);
//		//projected.rows.foreach(println) -- Scala语言循环便利打印这个新得到的矩阵方式
//		System.out.println(projected.numRows()+"行"+"\n"+projected.numCols()+"列");
//	}
//
//
//	@Autowired
//	TableChildMapper tableChildMapper;
//	@Test
//	public void testTableData(){
//		List<TableChild> tcList ;
//		TableChildExample tce = new TableChildExample();
//		tce.createCriteria().andBdpfMllibCreateIdEqualTo("4028d0d85f0e25a9015f0ea475dc000a");
//		tcList = tableChildMapper.selectByExample(tce);
//
//		//List<Map<String,Object>> list = FlowBussiness.queryColumnValues(tcList,"test","");
//
//		//System.out.println(list.get(1).get("c2"));
//
//	}
//	@Autowired
//	StandardizationAssemblyMapper standardizationAssemblyMapper;
//	@Autowired
//	StandardizationChildAssemblyMapper standardizationChildAssemblyMapper;
//	@Autowired
//	StandardizationChildAssemblyService standardizationChildAssemblyService;
//	@Test
//	public void testStandardizationAssembly(){
//		StandardizationAssemblyVo o = new StandardizationAssemblyVo();
//		o.setBdpfMllibAssemblyId("4028d0d85f0e25a9015f0ea475dc000a");
//		o.setCoreNumber(5);
//
//		StandardizationAssemblyVo o1 = new StandardizationAssemblyVo();
//		o1.setBdpfMllibAssemblyId("4028d0d85f0e25a9015f0ea475dc000a");
//		o1.setCoreNumber(5);
//		o1.setMemory(10);
//		o1.setId("4028d0d85f342ab7015f342ab7ac0000");
//
//		StandardizationAssembly sa = new StandardizationAssembly();
//		sa.setBdpfMllibAssemblyId(o.getBdpfMllibAssemblyId());
//		sa.setCoreNumber(o.getCoreNumber());
//		sa.setMemory(o.getMemory());
//
//		sa.setBdpfMllibAssemblyId(o1.getBdpfMllibAssemblyId());
//		sa.setCoreNumber(o1.getCoreNumber());
//		sa.setMemory(o1.getMemory());
//
//
//		if (StringUtils.isBlank(o1.getId())) {
//			sa.setId(StringTools.getUUID());
//			standardizationAssemblyMapper.insertSelective(sa);
//		}else if (o1.getColumnName() == null || o1.getColumnName().length < 1){//执行调优参数更新
//			sa.setId(o1.getId());
//			standardizationAssemblyMapper.updateByPrimaryKeySelective(sa);
//		}
//
//	}
//
//	@Test
//	public void testStandardizationChildAssembly() {
//		StandardizationAssemblyVo o = new StandardizationAssemblyVo();
//		o.setId("4028d0d85f342ab7015f342ab7ac0000");
//		o.setStandardizationChildAssemblyId(new String[]{"4028d0d85f344474015f344474390000","4028d0d85f344474015f344474390002",""});
//		o.setCoreNumber(5);
//		o.setColumnName(new String []{"age","sex","email"});
//		o.setColumnType(new String[]{"string","boolean","string"});
//
//		standardizationChildAssemblyService.save(o);
//	}
//	@Test
//	public void testMap(){
//		Map<String,Object> map = new HashMap<>();
//		map.put(null,"Hello");
//		map.put("","Java");
//
//		System.out.println("-------null ----"+map.get(null)+"-------  ----"+map.get(""));
//	}
//	@Autowired
//	RandomSamplingAssemblyService randomSamplingAssemblyService;
//	@Autowired
//	StandardizationAssemblyService standardizationAssemblyService;
//
//
//	@Test
//	public void testData(){
//		RandomSamplingAssembly rsa = randomSamplingAssemblyService.selectByPrimaryKey("");
//		System.out.println("----------"+rsa);
//	}
//}
