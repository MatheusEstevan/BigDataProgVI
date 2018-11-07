package MATHEUSGREG.AULA0611;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 SparkConf conf = new SparkConf();
         conf.setAppName("MatheusGregory");
         conf.setMaster("local[*]");

         JavaSparkContext context = new JavaSparkContext(conf);
         JavaRDD<String> acessos1 = context.textFile("In/nasa_19950701.tsv");
         JavaRDD<String> acessos2 = context.textFile("In/nasa_19950801.tsv");
         JavaRDD<String> primeiroMap = acessos1.filter(f -> !f.equals("host")).map(f -> f.split("\\t")[0]);
         JavaRDD<String> segundoMap = acessos2.map(f -> f.split("\\t")[0]);
         JavaRDD<String> interseccao = primeiroMap.intersection(segundoMap);
         List<String> arrayLinhas = interseccao.collect();
         
         arrayLinhas.forEach(f -> System.out.println(f));
    }
}
