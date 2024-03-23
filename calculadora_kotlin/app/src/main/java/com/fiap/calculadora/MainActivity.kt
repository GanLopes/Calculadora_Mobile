package com.fiap.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.fiap.calculadora.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculo = binding.TextViewResultado

        binding.Button1.setOnClickListener {
            calculo.text = "${calculo.text}1"
        }

        binding.Button2.setOnClickListener {
            calculo.text = "${calculo.text}2"
        }

        binding.Button3.setOnClickListener {
            calculo.text = "${calculo.text}3"
        }

        binding.Button4.setOnClickListener {
            calculo.text = "${calculo.text}4"
        }

        binding.Button5.setOnClickListener {
            calculo.text = "${calculo.text}5"
        }

        binding.Button6.setOnClickListener {
            calculo.text = "${calculo.text}6"
        }

        binding.Button7.setOnClickListener {
            calculo.text = "${calculo.text}7"
        }

        binding.Button8.setOnClickListener {
            calculo.text = "${calculo.text}8"
        }

        binding.Button9.setOnClickListener {
            calculo.text = "${calculo.text}9"
        }

        binding.Button0.setOnClickListener {
            calculo.text = "${calculo.text}0"
        }

        binding.ButtonDivisao.setOnClickListener {
            calculo.text = "${calculo.text}/"
        }

        binding.ButtonMult.setOnClickListener {
            calculo.text = "${calculo.text}*"
        }

        binding.ButtonSubtracao.setOnClickListener {
            calculo.text = "${calculo.text}-"
        }

        binding.ButtonSoma.setOnClickListener {
            calculo.text = "${calculo.text}+"
        }

        binding.ButtonPorcentagem.setOnClickListener {
            calculo.text = "${calculo.text}%"
        }

        binding.ButtonTrocaSinal.setOnClickListener {
            var resultado = trocarSinais(calculo.text.toString())
            binding.TextViewResultado.text = resultado.toString()
        }

        binding.ButtonClear.setOnClickListener {
            calculo.text = ""
            binding.TextViewResultado.text = ""
        }

        binding.ButtonIgual.setOnClickListener {
            var resultado = calcularExpressao(calculo.text.toString())
            binding.TextViewResultado.text=resultado.toString()
        }
    }

    fun trocarSinais(expressao: String):String {
        var expressaoTrocaSinais = ""

        for (caractere in expressao) {
            when (caractere) {
                '+' -> expressaoTrocaSinais += '-'
                '-' -> expressaoTrocaSinais += '+'
                else -> expressaoTrocaSinais += caractere
            }
        }

        return expressaoTrocaSinais
    }

    fun calcularExpressao(expressao: String): Double {
        var resultado = 0.0
        var numeroAtual = 0.0
        var operador = '+'
        var porcentagem = false

        for (i in expressao.indices) {
            val caractere = expressao[i]
            if (caractere.isDigit() || caractere == '.') {
                numeroAtual = numeroAtual * 10 + (caractere - '0')
            } else if (caractere == '%') {
                porcentagem = true
            }
            if ((!caractere.isDigit() && caractere != '.' && caractere != ' ') || i == expressao.length - 1) {
                when {
                    porcentagem -> {
                        numeroAtual /= 100
                        when (operador) {
                            '+' -> resultado += resultado * numeroAtual
                            '-' -> resultado -= resultado * numeroAtual
                            '*' -> resultado *= resultado * numeroAtual
                            '/' -> resultado /= resultado * numeroAtual
                        }
                        porcentagem = false
                    }
                    else -> {
                        when (operador) {
                            '+' -> resultado += numeroAtual
                            '-' -> resultado -= numeroAtual
                            '*' -> resultado *= numeroAtual
                            '/' -> resultado /= numeroAtual
                        }
                    }
                }
                operador = caractere
                numeroAtual = 0.0
            }
        }
        return resultado
    }
}