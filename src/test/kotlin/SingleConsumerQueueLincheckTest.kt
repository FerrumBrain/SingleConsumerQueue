import org.example.SingleConsumerQueue
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*

@StressCTest
class SingleConsumerQueueLincheckTest {
    private val queue = SingleConsumerQueue<Int>()

    @Operation
    fun enqueue(value: Int) = queue.enqueue(value)

    @Operation(nonParallelGroup = "consumers")
    fun dequeue() = queue.dequeue()

    @Operation(nonParallelGroup = "consumers")
    fun peek() = queue.peek()

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelCheckingTest() = ModelCheckingOptions().check(this::class)
}
