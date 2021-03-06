package hruntek.second;

import hruntek.Hruntek;
import hruntek.HruntekWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondReducer extends Reducer<Text, HruntekWritable, Text, HruntekWritable> {

	@Override
	public void reduce(Text key, Iterable<HruntekWritable> values, Context context) throws IOException, InterruptedException {
		HruntekWritable hruntek = new HruntekWritable();
		values.forEach(hruntek::combine);
		context.write(key, hruntek);

		context.getCounter(Hruntek.GROUP, Hruntek.TYPE).increment(1L);
	}
}
