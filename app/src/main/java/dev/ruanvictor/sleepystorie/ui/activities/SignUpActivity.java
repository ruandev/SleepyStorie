package dev.ruanvictor.sleepystorie.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.data.model.User;
import dev.ruanvictor.sleepystorie.utils.MyConstants;
import dev.ruanvictor.sleepystorie.utils.UIUtil;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textName, textEmail, textBirthday, textCelular, textAddress, textCity, textCEP, textPassword, textConfirmPassword;
    private TextInputLayout textNameLayout, textEmailLayout, textBirthdayLayout, textCelularLayout, textAddressLayout, textCityLayout, textCEPLayout, textPasswordLayout, textConfirmPasswordLayout;
    private Spinner uf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textName = findViewById(R.id.textName);
        textNameLayout = findViewById(R.id.textNameLayout);
        textEmail = findViewById(R.id.textEmail);
        textEmailLayout = findViewById(R.id.textEmailLayout);
        textBirthday = findViewById(R.id.textBirthday);
        textBirthdayLayout = findViewById(R.id.textBirthdayLayout);
        textCelular = findViewById(R.id.textCelular);
        textCelularLayout = findViewById(R.id.textCelularLayout);
        textAddress = findViewById(R.id.textAddress);
        textAddressLayout = findViewById(R.id.textAddressLayout);
        textCity = findViewById(R.id.textCity);
        textCityLayout = findViewById(R.id.textCityLayout);
        textCEP = findViewById(R.id.textCEP);
        textCEPLayout = findViewById(R.id.textCEPLayout);
        textPassword = findViewById(R.id.textPassword);
        textPasswordLayout = findViewById(R.id.textPasswordLayout);
        textConfirmPassword = findViewById(R.id.textConfirmPassword);
        textConfirmPasswordLayout = findViewById(R.id.textConfirmPasswordLayout);

        uf = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uf.setAdapter(adapter);


        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSignUp) {
            boolean isValidName = validateFieldRequired(textName, textNameLayout);
            boolean isValidEmail = validateFieldRequired(textEmail, textEmailLayout);
            boolean isValidBirthday = validateFieldRequired(textBirthday, textBirthdayLayout);
            boolean isValidCelular = validateFieldRequired(textCelular, textCelularLayout);
            boolean isValidAddress = validateFieldRequired(textAddress, textAddressLayout);
            boolean isValidCity = validateFieldRequired(textCity, textCityLayout);
            boolean isValidCEP = validateFieldRequired(textCEP, textCEPLayout);
            boolean isValidPassword = validateFieldRequired(textPassword, textPasswordLayout);
            boolean isValidConfirmPassword = validConfirmPassword();
            if (isValidName && isValidEmail && isValidBirthday && isValidCelular && isValidAddress && isValidCity && isValidCEP && isValidPassword && isValidConfirmPassword) {
                createUser();
            }
        }
    }

    private void createUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(textEmail.getText().toString(), textPassword.getText().toString()).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {

                User user = User.builder()
                        .address(textAddress.getText().toString())
                        .birthday(textBirthday.getText().toString())
                        .celular(textCelular.getText().toString())
                        .cep(textCEP.getText().toString())
                        .cep(uf.getSelectedItem().toString())
                        .build();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(MyConstants.USERS);
                reference.child(task.getResult().getUser().getUid())
                        .setValue(user);

                finish();
            }

            try{
                throw task.getException();
            } catch (FirebaseAuthWeakPasswordException e) {
                textPasswordLayout.setError(getString(R.string.password_weak));
                textPasswordLayout.setErrorEnabled(true);
                Toast.makeText(SignUpActivity.this, "Verifique os erros no formulário!", Toast.LENGTH_LONG).show();
            } catch (FirebaseAuthUserCollisionException e) {
                Toast.makeText(SignUpActivity.this, "Usuário já existe!", Toast.LENGTH_LONG).show();
            } catch (FirebaseAuthInvalidCredentialsException e) {
                textEmailLayout.setError("Email inválido!");
                textEmailLayout.setErrorEnabled(true);
                Toast.makeText(SignUpActivity.this, "Verifique os erros no formulário!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e("SIGNUP", "Erro ao criar usuário: " + e.getMessage());
                e.printStackTrace();
                Toast.makeText(SignUpActivity.this, "Erro ao criar um usuário!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateFieldRequired(TextInputEditText field, TextInputLayout layout) {
        if (field.getText().toString().isEmpty()) {
            layout.setError(getString(R.string.field_required));
            layout.setErrorEnabled(true);
        } else {
            UIUtil.clearErrorStyle(layout);
        }

        return (layout.getError() == null);
    }

    private boolean validConfirmPassword() {
        if(validateFieldRequired(textConfirmPassword, textConfirmPasswordLayout)) {
            return true;
        }

        String password = textPassword.getText().toString();
        String confirmPassword = textConfirmPassword.getText().toString();
        if(!confirmPassword.equals(password)) {
            textConfirmPasswordLayout.setError(getString(R.string.password_not_match));
            textConfirmPasswordLayout.setErrorEnabled(true);
        } else {
            UIUtil.clearErrorStyle(textConfirmPasswordLayout);
        }

        return (textConfirmPasswordLayout.getError() == null);
    }
}
