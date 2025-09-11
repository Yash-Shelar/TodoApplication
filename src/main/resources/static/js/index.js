const editableTitles = document.querySelectorAll('p[id^="editable-title-"]');
const editableDescriptions = document.querySelectorAll('p[id^="editable-description-"]');

const filterResetButton = document.getElementById("filter-reset-btn");
filterResetButton.addEventListener("click", async () => {
  try {
    const response = await fetch("http://localhost:8080/api/todo/reset-filter", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({})
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    window.location.replace("/todo");
  } catch (error) {
    console.error('Error fetching data:', error);
  }
});

const filterApplyButton = document.getElementById("filter-apply-btn");
filterApplyButton.addEventListener("click", async () => {
  try {
    const filterStatus = document.getElementById("filterStatus").value;
    const filterDate = document.getElementById("filterDate").value;
    const filterFrom = document.getElementById("filterFrom").value;
    const filterTo = document.getElementById("filterTo").value;

    const response = await fetch("http://localhost:8080/api/todo/apply-filter", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        "status": filterStatus,
        "date": filterDate,
        "timeFrom": filterFrom,
        "timeTo": filterTo
      })
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    window.location.replace("/todo");
  } catch (error) {
    console.error('Error fetching data:', error);
  }
});

editableTitles.forEach(editableTitle => {
  let originalTitle = "";
  editableTitle.addEventListener("focusin", async () => {
    originalTitle = editableTitle.innerText.trim();
  });

  editableTitle.addEventListener("focusout", async () => {
    const newTitle = editableTitle.innerText.trim();
    if (newTitle !== originalTitle) {
      shouldEditTitle = false;
      const todoId = editableTitle.dataset.id;
      const newTitle = editableTitle.innerText.trim();

      try {
        const response = await fetch(`http://localhost:8080/api/todo/update-title-description/${todoId}`, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ title: newTitle })
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        window.location.replace("/todo");
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
  });
});

editableDescriptions.forEach(editableDescription => {
  let originalDescription = "";
  editableDescription.addEventListener("focusin", async () => {
    originalDescription = editableDescription.innerText.trim();
  });

  editableDescription.addEventListener("focusout", async () => {
    const newDescription = editableDescription.innerText.trim();
    if (newDescription !== originalDescription) {
      shouldEditTitle = false;
      const todoId = editableDescription.dataset.id;
      const newDescription = editableDescription.innerText.trim();

      try {
        const response = await fetch(`http://localhost:8080/api/todo/update-title-description/${todoId}`, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ description: newDescription })
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        window.location.replace("/todo");
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
  });
});