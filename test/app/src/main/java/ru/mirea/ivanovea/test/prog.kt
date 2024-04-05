package wordpair

import org.apache.hadoop.conf.Configuration
import java.io.IOException
import java.util.Locale
import java.util.StringTokenizer
import java.util.regex.Pattern

object WordPair {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val conf = Configuration()
        val files: Array<String> = GenericOptionsParser(conf, args).getRemainingArgs()
        val input = Path(files[0])
        val output = Path(files[1])
        val job = Job(conf, "word pair")
        job.setJarByClass(WordPair::class.java)
        job.setMapperClass(MapForWordCount::class.java)
        job.setReducerClass(ReduceForWordCount::class.java)
        job.setOutputKeyClass(Text::class.java)
        job.setOutputValueClass(IntWritable::class.java)
        FileInputFormat.addInputPath(job, input)
        FileOutputFormat.setOutputPath(job, output)
        System.exit(if (job.waitForCompletion(true)) 0 else 1)
    }

    class MapForWordCount : Mapper<LongWritable?, Text?, Text?, IntWritable?>() {
        @Throws(IOException::class, InterruptedException::class)
        fun map(key: LongWritable?, value: Text, con: Context) {
            val text = nopunct(value.toString())
            var secondword = ""
            var firstword = ""
            val itr = StringTokenizer(text)
            if (itr.hasMoreTokens()) {
                firstword = itr.nextToken()
                secondword = firstword
            }
            while (itr.hasMoreTokens()) {
                firstword = secondword
                secondword = itr.nextToken()
                firstword = "$firstword $secondword"
                val outputKey = Text(firstword.lowercase(Locale.getDefault()))
                val outputValue = IntWritable(1)
                con.write(outputKey, outputValue)
                if (!itr.hasMoreTokens()) {
                    outputKey.set(secondword.lowercase(Locale.getDefault()))
                    con.write(outputKey, outputValue)
                }
            }
        }

        companion object {
            fun nopunct(s: String?): String {
                val pattern = Pattern.compile("[^A-Z a-z А-Я а-я]")
                val matcher = pattern.matcher(s)
                return matcher.replaceAll(" ")
            }
        }
    }

    class ReduceForWordCount : Reducer<Text?, IntWritable?, Text?, IntWritable?>() {
        @Throws(IOException::class, InterruptedException::class)
        fun reduce(word: Text?, values: Iterable<IntWritable?>, con: Context) {
            var sum = 0
            for (value in values) {
                sum += value.get()
            }
            con.write(word, IntWritable(sum))
        }
    }
}
