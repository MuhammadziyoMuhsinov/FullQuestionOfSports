package uz.muhammadziyo.fullquestionofsports

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.muhammadziyo.fullquestionofsports.databinding.FragmentTennisBinding
import uz.muhammadziyo.fullquestionofsports.databinding.FragmentVolleyballBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemChekDialogBinding
import uz.muhammadziyo.fullquestionofsports.databinding.ItemDialogBinding
import uz.muhammadziyo.fullquestionofsports.models.MyData
import uz.muhammadziyo.fullquestionofsports.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentVolleyball : Fragment() {

    private lateinit var binding: FragmentVolleyballBinding
    private lateinit var list: ArrayList<Question>
    private var position: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVolleyballBinding.inflate(layoutInflater)


        Handler().postDelayed(object : Runnable {
            override fun run() {
                binding.splash.visibility = View.INVISIBLE
            }
        }, 3000)
        loadList()
        loadQuestion()

        binding.answerA.setOnClickListener {
            chekQuestion(1)
        }

        binding.answerB.setOnClickListener {
            chekQuestion(2)
        }


        return binding.root
    }


    private fun loadQuestion() {
        binding.question.text = list[position].question
        binding.txtAnswer.text = list[position].answer1
        binding.txtAnswerB.text = list[position].answer2
    }

    private fun loadList() {
        list = ArrayList()

        list.add(Question("How many people on each team are in the court?",
            "6",
            "10",
            1
        ))

        list.add(Question("What was the original name of volleyball?",
            "Volley Ball",
            "Lacrosse",
            1
        ))

        list.add(Question("When was volleyball created?",
            "1895",
            "2004",
            1
        ))
        list.add(Question("Who created volleyball?",
            "John A. Belstrad",
            "William G. Morgan",
            2
        ))
        list.add(Question("When were the first volleyball World Championships held?",
            "1949 / 1952",
            "1872 / 1918",
            1
        ))
        list.add(Question("How is the serving team chosen?",
            " A coin toss",
            "Referee’s choice",
            1
        ))
        list.add(Question("What are points called in volleyball?",
            "Goals",
            "Points",
            2
        ))
        list.add(Question("If you score a point doing a spike, what is it called?",
            "Kill",
            "Hit",
            1
        ))
        list.add(Question("What is the number of times a team can hit a ball without passing it over the net?",
            "1",
            "3",
            2
        ))
        list.add(Question("What is the player who specialises in defense called?",
            "Libero",
            "Freestyle",
            1
        ))

    }

    private fun chekQuestion(answer: Int) {

        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                MyData.correct += 1

                chekDialog(1)
                position = 0
                loadQuestion()
                alertDialog()
            } else {
                position += 1
                MyData.correct += 1
                 chekDialog(1)
                loadQuestion()
            }

        } else {
            if (list.size == position + 1) {
                MyData.incorrect += 1
                position = 0
                chekDialog(2)
                loadQuestion()
                alertDialog()
            } else {
                MyData.incorrect += 1
                chekDialog(2)
                position += 1
                loadQuestion()

            }

        }

    }

    private fun chekDialog(answer: Int) {
        val dialog = BottomSheetDialog(binding.root.context, R.style.NewDialog)
        val itemDialog = ItemChekDialogBinding.inflate(layoutInflater)
        if (answer == 1) {
            itemDialog.chek.text = "Correct!"
            itemDialog.chek.setTextColor(Color.GREEN)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        } else {
            itemDialog.chek.text = "Wrong!"
            itemDialog.chek.setTextColor(Color.RED)
            dialog.setContentView(itemDialog.root)
            dialog.show()
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    dialog.dismiss()
                }
            }, 1000)
        }

    }

    private fun alertDialog() {
        binding.xira.visibility = View.VISIBLE
        val dialog = AlertDialog.Builder(binding.root.context, R.style.NewDialog).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val myDateObj = LocalDateTime.now()
                val myFormatObj: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")

                val formattedDate = myDateObj.format(myFormatObj)

                itemDialogBinding.txtCorrect.text = "correct answer: ${MyData.correct}"
                itemDialogBinding.txtWrong.text = "wrong answer: ${MyData.incorrect}"
                itemDialogBinding.date.text = formattedDate

            }
        }
        dialog.setView(itemDialogBinding.root)
        dialog.setOnDismissListener {
            binding.xira.visibility = View.INVISIBLE
            MyData.correct = 0
            MyData.incorrect = 0
        }
        dialog.show()


    }


}

