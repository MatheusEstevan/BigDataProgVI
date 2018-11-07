package MATHEUS.Aula0711;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
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

         //multiplicacao
         JavaRDD<Integer> valores = context.parallelize(Arrays.asList(1,2,3,4,5,6,7,8));
         Integer multiplicacao = valores.reduce((x,y) -> x*y);
//         System.out.println(multiplicacao);
         
         
         
         //atv
         JavaRDD<String> arquivo = context.textFile("In/prime_nums.text");
         JavaRDD<String> numerosStr = arquivo.flatMap(f-> Arrays.asList(f.split("\t")).iterator());
         JavaRDD<Integer> numerosInt = numerosStr.map(f ->Integer.parseInt(f.trim()));
         long result = numerosInt.reduce((x,y) -> x + y);
         System.out.println(result);
         
    }
}
