package com.example.studycompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Dialog(
    context: Context,
    setShowDialog: (Boolean) -> Unit = {}
) {

    AlertDialog(
        onDismissRequest = { setShowDialog(false) },
        confirmButton = {
            TextButton(onClick = {
                Toast.makeText(context, "Enviado com sucesso!", Toast.LENGTH_SHORT).show()
                setShowDialog(false)
            }) {
                Text("Cofirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                setShowDialog(false)
            }) {
                Text("Cancelar")
            }
        },
        title = { Text(text = "Mensagem de confirmação", fontSize = 20.sp) },
        text = { Text(text = "Confirma o envio.") },
        modifier = Modifier.fillMaxWidth()
    )
}