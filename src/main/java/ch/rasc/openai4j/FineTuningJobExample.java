/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j;

public class FineTuningJobExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var jobs = client.fineTuningJobs.list();
		for (var job : jobs.data()) {
			System.out.println(job);

			var j = client.fineTuningJobs.retrieve(job.id());
			System.out.println(j.id());

			var events = client.fineTuningJobs.listEvents(job.id());
			for (var event : events.data()) {
				System.out.println(event);
			}

			// var c = client.cancel(job.id());
			// System.out.println(c);
		}
	}

}
