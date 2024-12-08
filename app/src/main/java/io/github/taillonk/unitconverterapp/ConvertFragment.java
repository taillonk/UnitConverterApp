package io.github.taillonk.unitconverterapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner categorySpinner;
    private Spinner convertFromSpinner;
    private Spinner convertToSpinner;
    private ArrayAdapter<CharSequence> categoryAdapter;
    private ArrayAdapter<CharSequence> convertFromAdapter;
    private ArrayAdapter<CharSequence> convertToAdapter;
    private TextView resultTextView;
    private EditText valueToConvert;
    private Button convertButton;
    private Button resetButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_convert, container, false);

        //Initialize spinners
        categorySpinner = view.findViewById(R.id.category_spinner);
        convertFromSpinner = view.findViewById(R.id.convert_from_spinner);
        convertToSpinner = view.findViewById(R.id.convert_to_spinner);

        //Initialize adapters
        // category
        categoryAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        //from
        convertFromAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_placeholder, android.R.layout.simple_spinner_item);
        convertFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertFromSpinner.setAdapter(convertFromAdapter);
        //to
        convertToAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_placeholder, android.R.layout.simple_spinner_item);
        convertToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertToSpinner.setAdapter(convertToAdapter);

        //Spinner Listeners
        categorySpinner.setOnItemSelectedListener(this);
        convertFromSpinner.setOnItemSelectedListener(this);
        convertToSpinner.setOnItemSelectedListener(this);

        //Initialize value to convert EditText
        valueToConvert = view.findViewById(R.id.value_to_convert);

        //Initialize result TextView
        resultTextView = view.findViewById(R.id.converted_value);

        //Initialize Convert Button
        convertButton = view.findViewById(R.id.convert_button);
        convertButton.setOnClickListener(this);

        //Setup for reset button
        resetButton = view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(v -> {
            valueToConvert.setText("");
            resultTextView.setText("");
            categorySpinner.setAdapter(categoryAdapter);
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.category_spinner) {
            // Update convertFromSpinner and convertToSpinner based on selected option in categorySpinner
            int selectedOption = position;
            switch (selectedOption) {
                case 0:
                    convertFromAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_placeholder, android.R.layout.simple_spinner_item);
                    convertFromSpinner.setAdapter(convertFromAdapter);
                    convertToAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_placeholder, android.R.layout.simple_spinner_item);
                    convertToSpinner.setAdapter(convertToAdapter);
                    break;
                case 1:
                    convertFromAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.length_array, android.R.layout.simple_spinner_item);
                    convertFromSpinner.setAdapter(convertFromAdapter);
                    convertToAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.length_array, android.R.layout.simple_spinner_item);
                    convertToSpinner.setAdapter(convertToAdapter);
                    break;
                case 2:
                    convertFromAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.temp_array, android.R.layout.simple_spinner_item);
                    convertFromSpinner.setAdapter(convertFromAdapter);
                    convertToAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.temp_array, android.R.layout.simple_spinner_item);
                    convertToSpinner.setAdapter(convertToAdapter);
                    break;
                case 3:
                    convertFromAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.weight_array, android.R.layout.simple_spinner_item);
                    convertFromSpinner.setAdapter(convertFromAdapter);
                    convertToAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.weight_array, android.R.layout.simple_spinner_item);
                    convertToSpinner.setAdapter(convertToAdapter);
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.convert_button) {
            int category = categorySpinner.getSelectedItemPosition();
            int unitFrom = convertFromSpinner.getSelectedItemPosition();
            int unitTo = convertToSpinner.getSelectedItemPosition();
            String valueStr = valueToConvert.getText().toString();
            double valueD;
            if (category == 0) {
                Toast.makeText(getContext(), "SELECT A CATEGORY AND CONVERSION TYPE", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    valueD = Double.parseDouble(valueStr);
                    if (category == 1) {
                        if (unitFrom == 0 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.inchesToMeters(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 0 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.inchesToFeet(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.metersToInches(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.metersToFeet(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.feetToInches(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Length.feetToMeters(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else {
                            resultTextView.setText(String.format("%.2f %s %s", valueD, " ", convertToSpinner.getSelectedItem()));
                        }
                    } else if (category == 2) {
                        if (unitFrom == 0 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.celsiusToFahrenheit(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 0 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.celsiusToKelvin(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.fahrenheitToCelsius(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.fahrenheitToKelvin(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.kelvinToCelsius(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Temp.kelvinToFahrenheit(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else {
                            resultTextView.setText(String.format("%.2f %s %s", valueD, " ", convertToSpinner.getSelectedItem()));
                        }
                    } else if (category == 3) {
                        if (unitFrom == 0 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.gramsToPounds(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 0 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.gramsToOunces(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.poundsToGrams(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 1 && unitTo == 2) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.poundsToOunces(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 0) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.ouncesToGrams(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else if (unitFrom == 2 && unitTo == 1) {
                            resultTextView.setText(String.format("%.2f %s %s", Weight.ouncesToPounds(valueD), " ", convertToSpinner.getSelectedItem()));
                        } else {
                            resultTextView.setText(String.format("%.2f %s %s", valueD, " ", convertToSpinner.getSelectedItem()));
                        }
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "ENTER VALUE TO CONVERT", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}