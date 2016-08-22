/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package library_project.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import library_project.models.Book;
import library_project.repos.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Iterator;

@RunWith(SpringRunner.class)
public class BooksControllerTests {
	private MockMvc mockMvc;
    private BookRepository bookRepo;

	@Before
	public void setUp() {
	    bookRepo = Mockito.mock(BookRepository.class);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BooksController(bookRepo)).build();
	}

	@Test
	public void displayAllBooks() throws Exception {
        Iterator<Book> bookIt = Arrays.asList(
                new Book(1, "123ABC", "After Dark", "Murukami", "15-May-2002"),
                new Book(2, "125BFD", "Kafka on the Shore", "Murukami", "12-Aug-2000")).iterator();
	    when(bookRepo.getAll()).thenReturn(bookIt);

		this.mockMvc.perform(get("/api/books/books"))
				.andDo(print())
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].isbn").value("125BFD"))
                .andExpect(jsonPath("$[1].title").value("Kafka on the Shore"))
                .andExpect(jsonPath("$[1].author").value("Murukami"))
                .andExpect(jsonPath("$[1].publishDate").value("12-Aug-2000"));
	}
}
