# Filling missing values
## Introduction
An application for filling in missing data using entropy calculated with Hamming and Euclidean distances, as well as their negation.

## Technologies

The technologies used are:
- Java
- JavaFX

## Application


The application allows for controlled removal of a portion of the data, which is then displayed in a table. The algorithm normalizes the data and transforms it into interval values, extending them from a fuzzy set to an interval-valued fuzzy set. Next, the user can choose which metric should be applied for entropy calculation and classification, as well as the number of nearest neighbors to consider. The algorithm performs a series of calculations and finds the 'n' smallest entropies, based on which it fills in the missing data and classifies objects without a decision. Finally, the user receives the results, showing how well the algorithm handled classifying new objects using quality measures.

## Preview Application
### Landing Page
<div>
  <img width="80%" src="https://i.imgur.com/JpzQ0I4.png" alt="Home Page">
</div>

### Deleting Page
<div>
  <img width="80%" src="https://i.imgur.com/YRQkJaf.png" alt="Deleting Page">
</div>

### Data after normalization and interval transform
<div>
  <img width="80%" src="https://i.imgur.com/v57dE7Y.png" alt="Normalization and Intervals">
</div>

### Sample results
<div>
  <img width="80%" src="https://i.imgur.com/lsrmhql.png" alt="Results">
</div>
