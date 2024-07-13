let maxDate = new Date();
maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#ReservationDate', {
    locale: 'ja',
    minDate: 'today',
    maxDate: maxDate
});